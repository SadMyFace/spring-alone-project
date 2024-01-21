package com.project.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.www.domain.CommentVO;
import com.project.www.domain.PagingVO;

public interface CommentDAO {

	int postComment(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> getList(@Param("bno")long bno, @Param("pgvo")PagingVO pgvo);

	int editComment(CommentVO cvo);

	int deleteComment(long cno);

}
