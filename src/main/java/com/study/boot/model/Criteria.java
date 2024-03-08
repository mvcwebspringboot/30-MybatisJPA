package com.study.boot.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Criteria {
	
	@Builder.Default
	@Positive 
	private int page = 1;//현재페이지
	
	@Max(100)
	@Min(1)
	@Builder.Default
	private int size = 10;//페이지당 노출 게시물개수
	
	//페이징처리에 사용
	public Pageable getPageable(String prop) {
		return PageRequest.of(this.page-1,this.size, Sort.by(prop).descending());
	}
	
	public String getLink() {
		StringBuilder sb = new StringBuilder();
		sb.append("&page="+page);
		sb.append("&size="+size);
		
		return sb.toString();
		/*
		 * 생성되는 결과문자열이 '?'로 시작해서 사용하지 못함 
	    UriComponents link = UriComponentsBuilder.newInstance()
	    		.queryParam("page", page)
	    		.queryParam("size", size)
	    		.build();
	    return link.toUriString();
	    */
		
	}
	
}




















