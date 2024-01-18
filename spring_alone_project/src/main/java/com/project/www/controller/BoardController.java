package com.project.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.www.domain.BoardVO;
import com.project.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		
		log.info(">>> bvo >>> {}", bvo);
		
		int isOk = bsv.insertBoard(bvo);
		log.info("board insert " + ((isOk > 0) ? "success" : "fail"));
		
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		
		List<BoardVO> boardList = bsv.getList();
		
		m.addAttribute("list", boardList);
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") long bno, Model m) {
		log.info(">>> bno >>> " + bno);
		
		int isOk = bsv.updateReadCount(bno);
		log.info("readCount update " + ((isOk > 0) ? "success" : "fail"));

		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
}
