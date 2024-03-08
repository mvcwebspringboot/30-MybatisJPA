package com.study.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.boot.domain.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}
