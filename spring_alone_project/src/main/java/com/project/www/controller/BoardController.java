package com.project.www.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.www.domain.BoardDTO;
import com.project.www.domain.BoardVO;
import com.project.www.domain.FileVO;
import com.project.www.domain.PagingVO;
import com.project.www.handler.FileHandler;
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
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name = "files", required = false)MultipartFile[] files) {
		
		log.info(">>> bvo >>> {}", bvo);
		
		List<FileVO> flist = null;
		
		//fileHandler 생성 multipartfile -> flist
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.insertBoard(new BoardDTO(bvo, flist));
		
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
	
	//Principal을 줘서 아이디 비교 후 게시물 삭제, 수정 가능 여부 확인
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno") long bno, Model m, Principal p) {
		log.info(">>> bno >>> " + bno);
		
		int isOk = bsv.updateReadCount(bno);
		log.info("readCount update " + ((isOk > 0) ? "success" : "fail"));

		m.addAttribute("bdto", bsv.getDetail(bno));
		
		if(p != null) {
			log.info(">>> principal >> email > " + p.getName());
			String email = p.getName();
			m.addAttribute("userEmail", email);			
		}
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name = "files", required = false)MultipartFile[] files) {
		log.info(">>> bvo >>> {}", bvo);
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);				
		}
		int isOk = bsv.modify(new BoardDTO(bvo, flist));
		
		log.info("board modify " + ((isOk > 0) ? "success" : "fail"));
		
		
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String delete(@RequestParam("bno") long bno, RedirectAttributes re) {
		log.info(">>> bno >>> " + bno);
		
		int isOk = bsv.deleteBoard(bno);
		
		log.info("board delete " + ((isOk > 0) ? "success" : "fail"));
		
		re.addFlashAttribute("isDel", isOk);
		
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="/deleteImage/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteImage(@PathVariable("uuid") String uuid){
		log.info(">>> uuid >>> " + uuid);
		
		int isOk = bsv.deleteImage(uuid);
		
		log.info(">>> image delete >>> " + ((isOk > 0) ? "OK" : "Fail"));
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
