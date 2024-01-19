package com.project.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.www.domain.BoardVO;
import com.project.www.domain.PagingVO;
import com.project.www.handler.PagingHandler;
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
	public void list(Model m, PagingVO pgvo) {
		
		List<BoardVO> boardList = bsv.getList(pgvo);
		
		int totalCount = bsv.getTotalCount(pgvo);
		log.info(">>> total count >>> " + totalCount);
		
		PagingHandler ph  = new PagingHandler(pgvo, totalCount);
		log.info(">>> ph >>> " + ph);
		
		m.addAttribute("list", boardList);
		m.addAttribute("ph", ph);
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") long bno, Model m) {
		log.info(">>> bno >>> " + bno);
		
		int isOk = bsv.updateReadCount(bno);
		log.info("readCount update " + ((isOk > 0) ? "success" : "fail"));

		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info(">>> bvo >>> {}", bvo);
		
		int isOk = bsv.modify(bvo);
		
		log.info("board modify " + ((isOk > 0) ? "success" : "fail"));
		
		
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno, RedirectAttributes re) {
		log.info(">>> bno >>> " + bno);
		
		int isOk = bsv.deleteBoard(bno);
		
		log.info("board delete " + ((isOk > 0) ? "success" : "fail"));
		
		re.addFlashAttribute("isDel", isOk);
		
		return "redirect:/board/list";
	}
	
}
