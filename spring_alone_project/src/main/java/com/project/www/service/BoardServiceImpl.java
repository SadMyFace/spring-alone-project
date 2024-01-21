package com.project.www.service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.project.www.domain.BoardDTO;
import com.project.www.domain.BoardVO;
import com.project.www.domain.FileVO;
import com.project.www.domain.PagingVO;
import com.project.www.repository.BoardDAO;
import com.project.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO bdao;
	private final FileDAO fdao;
	
	@Override
	public int insertBoard(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOk = bdao.insertBoard(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		//bvo register 후 파일도 있다면...
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			//bno setting
			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno;
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		int isOk = bdao.updateCommentCount();
		
		if(isOk == 0) {
			log.info("getCommentCount error!!");
		}
		
		isOk = bdao.updateFileCount();
		
		if(isOk == 0) {
			log.info("getFileCount error!!");
		}
		
		return bdao.getList(pgvo);
	}

	@Override
	public int updateReadCount(long bno) {
		// TODO Auto-generated method stub
		return bdao.updateReadCount(bno);
	}
	
	@Transactional
	@Override
	public BoardDTO getDetail(long bno) {
		// TODO Auto-generated method stub
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		
		return bdto;
	}

	@Override
	public int modify(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOk = bdao.modify(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
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

	@Override
	public int deleteImage(String uuid) {
		// TODO Auto-generated method stub
		return fdao.deleteImage(uuid);
	}
}
