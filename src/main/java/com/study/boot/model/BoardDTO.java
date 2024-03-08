package com.study.boot.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	private Long bno;
	
	@Size(min = 3, max = 100)//글자길이
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String writer;
	
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
