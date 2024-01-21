package com.project.www.repository;

import java.util.List;

import com.project.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> selectListAllFile();

	List<FileVO> getFileList(long bno);

	int deleteImage(String uuid);

}