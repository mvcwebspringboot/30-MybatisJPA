package com.study.boot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.boot.model.BoardDTO;
import com.study.boot.service.BoardService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	//@Test
	public void testRegister() {
		log.info("★★★★★ "+boardService.getClass().getName());
		
		BoardDTO dto = BoardDTO.builder()
				.title("샘플 제목 ...")
				.content("샘플 내용 ...")
				.writer("사용자00")
				.build();
		
		Long bno = boardService.register(dto);
		
		log.info("★★★★★ bno = "+bno);
	}
	
	@Test
	public void testRead() {
		Long bno = 202L;
		
		try {
			BoardDTO dto = boardService.readOne(bno);
			log.info("★★★★★ dto = "+dto);			
		}catch(Exception e) {
			log.info("★★★★★ 데이터 없음");
		}
	}
	
	//@Test
	public void testModify() {
		Long bno = 202L;
		BoardDTO dto = boardService.readOne(bno);
		dto.setTitle("수정된 제목");
		dto.setContent("수정된 내용");
		
		boardService.modify(dto);
		
		dto = boardService.readOne(bno);
		log.info("★★★★★ dto = "+dto);		
		
	}
	
	//@Test
	public void testDelete() {
		Long bno = 202L;
		boardService.delete(bno);
	}
	
	
}














