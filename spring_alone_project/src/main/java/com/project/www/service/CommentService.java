package com.project.www.service;

import com.project.www.domain.CommentVO;
import com.project.www.domain.PagingVO;
import com.project.www.handler.PagingHandler;

public interface CommentService {

	int postComment(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int editComment(CommentVO cvo);

	int deleteComment(long cno);

}
