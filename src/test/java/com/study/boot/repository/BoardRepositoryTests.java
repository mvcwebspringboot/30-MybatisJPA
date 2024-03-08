package com.study.boot.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.study.boot.domain.BoardEntity;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void testPaging() {
		
		//페이징 처리를 위한 Pageable 타입의 객체를 추출
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		//findAll()의 리턴타입으로 나오는 Page<T> 타입은 페이징 처리에 필요한 여러가지 정보를 제공한다.
		Page<BoardEntity> result = boardRepository.findAll(pageable);
		
		log.info("total count = " + result.getTotalElements());
		log.info("total page = " + result.getTotalPages());
		log.info("page number = " + result.getNumber());
		log.info("page size = " + result.getSize());

		List<BoardEntity> todoList = result.getContent();
		
		todoList.forEach(board->log.info(board));
	}
	
	//@Test
	public void testDelete() {
		Long bno = 100L;
		
		boardRepository.deleteById(bno);
	}
	
	//@Test
	public void testSelect() {
		Long bno = 100L;
		
		Optional<BoardEntity> result = boardRepository.findById(bno);
		try{
			BoardEntity board = result.orElseThrow();
			log.info("★★★★★ board = "+board);			
			
		}catch(NoSuchElementException e) {
			log.info("해당 데이터가 없습니다.");
			
		}
	}
	
	//@Test
	public void testUpdate() {
		Long bno = 100L;
		
		Optional<BoardEntity> result = boardRepository.findById(bno);
		BoardEntity board = result.orElseThrow();
		
		board.change("아기공룡", "둘리");
		boardRepository.save(board);
		
		log.info("★★★★★ board = "+board);
	}
	
	//@Test
	public void testInsert() {
		//IntStream은 range와 rangeClosed 메소드로 범위를 지정할 수 있는데, Closed가 붙으면 끝 개수를 포함한다.
		IntStream.rangeClosed(1, 100).forEach(i->{
			BoardEntity board = BoardEntity.builder()
					.title("제목..."+i)
					.content("내용..."+i)
					.writer("글쓴이..."+i)
					.build();
			
			BoardEntity result = boardRepository.save(board);
			
			log.info("★★★★★ BNO: "+result.getBno());
		});
	}
	
	
}
