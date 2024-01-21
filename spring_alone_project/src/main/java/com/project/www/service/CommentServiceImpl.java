package com.project.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.www.domain.CommentVO;
import com.project.www.domain.PagingVO;
import com.project.www.handler.PagingHandler;
import com.project.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	
	private final CommentDAO cdao;

	@Override
	public int postComment(CommentVO cvo) {
		
		return cdao.postComment(cvo);
	}
	
	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cdao.getList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int editComment(CommentVO cvo) {
		
		return cdao.editComment(cvo);
	}

	@Override
	public int deleteComment(long cno) {
		
		return cdao.deleteComment(cno);
	}
	
}
