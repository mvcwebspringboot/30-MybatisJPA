package com.study.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.boot.model.BoardDTO;
import com.study.boot.model.Criteria;
import com.study.boot.model.PageResponseDTO;
import com.study.boot.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	//글보기
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("bno") Long bno, Criteria cri, Model model) {
		log.info("★★★★★ 글보기");
		log.info("★★★★★ 글보기"+cri);
		BoardDTO dto = boardService.readOne(bno);
		model.addAttribute("dto", dto);
		model.addAttribute("cri", cri);
	}
	
	//글삭제
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rattr) {
		boardService.delete(bno);
		
		return "redirect:/board/list?"+cri.getLink();
	}
	
	//글수정
	@PostMapping("/modify")
	public String modify(Criteria cri, @Valid BoardDTO dto, BindingResult result, RedirectAttributes rattr) {
		
		if(result.hasErrors()) {
			rattr.addFlashAttribute("errors", result.getAllErrors());
			rattr.addAttribute("bno",dto.getBno());
			return "redirect:/board/modify?"+cri.getLink();
		}
		
		boardService.modify(dto);
		rattr.addFlashAttribute("result", "수정성공");
		rattr.addAttribute("bno", dto.getBno());
		
		return "redirect:/board/read?"+cri.getLink();
	}
	
	//글목록
	@GetMapping("/list")
	public void list(Criteria cri ,Model model) {
		log.info("★★★★★ 리스트 보기");
		
		PageResponseDTO<BoardDTO> res = boardService.list(cri);
		model.addAttribute("res",res);
	}
	
	//글작성
	@GetMapping("/regist")
	public void regist() {
		log.info("★★★★★ 새글등록");
	}
	
	//글등록
	@PostMapping("/regist")
	public String regist(@Valid BoardDTO dto, BindingResult result, RedirectAttributes rattr) {
		
		if(result.hasErrors()) {
			log.info("★★★★★ 에러 발생");
			rattr.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/board/regist";
		}
		
		log.info("★★★★★ dto = "+ dto);
		Long bno = boardService.register(dto);
		rattr.addFlashAttribute("result", bno);
				
		return "redirect:/board/list";
	}
	
}
