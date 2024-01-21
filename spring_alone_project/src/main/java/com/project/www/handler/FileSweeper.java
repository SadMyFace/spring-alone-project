package com.project.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.www.domain.FileVO;
import com.project.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class FileSweeper {
	
	private final String BASE_PATH = "D:\\_myProject\\_java\\_fileUpload\\";
	
	private final FileDAO fdao;
	
	@Scheduled(cron="0 56 10 * * *")
	public void fileSeeper() {
		log.info(">>> FileSweeper Running start~!! : {}", LocalDateTime.now());
		
		List<FileVO> dbList = fdao.selectListAllFile();
		
		List<String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir() + File.separator + fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH + filePath + "_" + fileName);
			
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH + filePath + "_th_" + fileName);
			}
		}
		
		log.info("currFile >>> " + currFiles);
		
		LocalDate now = LocalDate.now();
		String today = now.toString(); // 2024-01-12
		today = today.replace("-", File.separator);
		
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjects = dir.listFiles();
		
		for(File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			if(!currFiles.contains(storedFileName)) {
				file.delete();
				log.info(">>> delete file >>> {} ", storedFileName);
			}
		}		
		
		log.info(">>> FileSweeper Running finish~!! : {}", LocalDateTime.now());
	}
}
