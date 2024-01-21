package com.project.www.repository;

import java.util.List;

import com.project.www.domain.BoardVO;
import com.project.www.domain.PagingVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	int updateReadCount(long bno);

	Object getDetail(long bno);

	int modify(BoardVO bvo);

	int deleteBoard(long bno);

	int getTotalCount(PagingVO pgvo);

}
