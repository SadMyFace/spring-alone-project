package com.project.www.service;

import java.util.List;

import com.project.www.domain.BoardDTO;
import com.project.www.domain.BoardVO;
import com.project.www.domain.PagingVO;

public interface BoardService {

	int insertBoard(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	int updateReadCount(long bno);

	BoardDTO getDetail(long bno);

	int modify(BoardDTO bdto);

	int deleteBoard(long bno);

	int getTotalCount(PagingVO pgvo);

	int deleteImage(String uuid);

}
