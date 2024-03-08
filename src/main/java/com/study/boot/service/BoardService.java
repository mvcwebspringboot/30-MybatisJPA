package com.study.boot.service;

import java.util.List;

import com.study.boot.model.BoardDTO;
import com.study.boot.model.Criteria;
import com.study.boot.model.PageResponseDTO;

public interface BoardService {
	Long register(BoardDTO dto);
	
	PageResponseDTO<BoardDTO> list(Criteria cri);
	
	BoardDTO readOne(Long bno);
	
	void modify(BoardDTO dto);
	
	void delete(Long bno);
}
