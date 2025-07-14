package com.jinanging.thebuild.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public static final String FILE_UPLOAD_PATH ="/Users/jinan/Desktop/Academy_java/springProject/Memo/upload/theBuild";
	
	public static String saveFile(long userId,MultipartFile file) {
		
		if(file == null) {
			return null;
		}
		
		// 파일 이름 유지 
		// 폴더(디렉토리 열어서 저장)
		// 사용자 정보를 폴더 이름으로  사용
		// 시간 정보 사용 
		// unix time 
		
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			//문자열 생성 실패
			return null;
		}
		
		// 파일 저장 
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 실제 파일이 저장 위치와  url 경로를 매핑하는 규칙
		
		return "/images/"+ directoryName + "/" + file.getOriginalFilename();
		
	}
	// 파일 삭제
		public static boolean removeFile(String filePath) {
			
			if(filePath == null) {
				return false;
			}
			String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
			
			Path path = Paths.get(fullFilePath);
			
			//상위 경로
			Path dirPath = path.getParent();
			
			try {
				Files.delete(path);
				Files.delete(dirPath);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
			return true;
			
			
			
		}

}
