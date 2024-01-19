package com.project.www.service;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.project.www.domain.BoardVO;
import com.project.www.domain.PagingVO;
import com.project.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO bdao;

	@Override
	public int insertBoard(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.insertBoard(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getList(pgvo);
	}

	@Override
	public int updateReadCount(long bno) {
		// TODO Auto-generated method stub
		return bdao.updateReadCount(bno);
	}

	@Override
	public Object getDetail(long bno) {
		// TODO Auto-generated method stub
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.modify(bvo);
	}

	@Override
	public int deleteBoard(long bno) {
		// TODO Auto-generated method stub
		return bdao.deleteBoard(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	}
}
