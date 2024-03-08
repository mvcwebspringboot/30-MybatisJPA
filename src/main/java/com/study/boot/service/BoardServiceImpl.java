package com.study.boot.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.study.boot.domain.BoardEntity;
import com.study.boot.model.BoardDTO;
import com.study.boot.model.Criteria;
import com.study.boot.model.PageResponseDTO;
import com.study.boot.repository.BoardRepository;
import com.study.boot.util.PagingUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final ModelMapper modelMapper;
	private final BoardRepository boardRepository;
	

	@Override
	public Long register(BoardDTO dto) {		
		BoardEntity board = modelMapper.map(dto, BoardEntity.class);
		Long bno = boardRepository.save(board).getBno();
		return bno;
	}


	@Override
	public PageResponseDTO<BoardDTO> list(Criteria cri) {
		
		Page<BoardEntity> result = boardRepository.findAll(cri.getPageable("bno"));
		

		//게시물리스트		
		List<BoardDTO> list = result.getContent().stream()
				.map(entity->modelMapper.map(entity, BoardDTO.class))
				.collect(Collectors.toList());
		
		
		//페이징에 필요한 데이터
		int displayPagiNum = 10;//한페이지 보이는0 페이지네이션개수
		int totalPage = result.getTotalPages();//전체페이지수
		int totalCount = (int)result.getTotalElements();//전체개시물개수
        PagingUtil pagingUtil = PagingUtil.builder()
        		.page(cri.getPage())
        		.size(cri.getSize())
        		.displayPagiNum(displayPagiNum)
        		.totalCount(totalCount)
        		.totalPage(totalPage)
        		.build(); 

        String paging = pagingUtil.makePaging();	
		
        
		PageResponseDTO<BoardDTO> resDto = PageResponseDTO.<BoardDTO>builder()
				.cri(cri)
				.paging(paging)
				.list(list)
				.build();
		
		return resDto;
	}


	@Override
	public BoardDTO readOne(Long bno) {
		Optional<BoardEntity> result = boardRepository.findById(bno);		
		BoardDTO dto = modelMapper.map(result.orElseThrow(), BoardDTO.class);
		return dto;
	}


	@Override
	public void modify(BoardDTO dto) {
		Optional<BoardEntity> result = boardRepository.findById(dto.getBno());
		BoardEntity entity = result.orElseThrow();
		entity.change(dto.getTitle(), dto.getContent());
		boardRepository.save(entity);
	}


	@Override
	public void delete(Long bno) {
		boardRepository.deleteById(bno);
		
		
	}

}
