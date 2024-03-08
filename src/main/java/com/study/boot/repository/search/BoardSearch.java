package com.study.boot.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.study.boot.domain.BoardEntity;

public interface BoardSearch {
	
	//searchAll()로 나아가기 위한 디딤돌 역할의 설명메소드(실제사용X)
	Page<BoardEntity> search1(Pageable pageable);
}
