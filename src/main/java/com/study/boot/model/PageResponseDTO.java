package com.study.boot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseDTO<T> {
	private Criteria cri;
	private String paging;//html 페이징코드
	private List<T> list;//게시물리스트
}
