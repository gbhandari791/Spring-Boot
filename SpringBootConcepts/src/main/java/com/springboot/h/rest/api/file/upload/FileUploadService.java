package com.springboot.h.rest.api.file.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	// final String DIR_PATH = "D:\\Gaurav\\Personal\\Version\\repo_springboot\\Spring-Boot\\SpringBootConcepts\\src\\main\\resources\\static\\files";
	String DIR_PATH = "uploads";
	public FileUploadService() throws IOException {	
		 File uploadDir = new File("uploads");
		 if(!uploadDir.exists()) {
			 uploadDir.mkdir();
		 }
	}
	
	public boolean uploadFile(MultipartFile file) {
		boolean isFileUploaded = false;
		try {
			
			
			String fileName = file.getOriginalFilename();
			String filePath = DIR_PATH + File.separator + fileName;
			
			/* Method-1 */
//			InputStream inputStream = file.getInputStream();
//			byte data[] = new byte[inputStream.available()];
//			inputStream.read(data);			
//			FileOutputStream fs = new FileOutputStream(filePath);
//			fs.write(data);
//			fs.flush();
//			fs.close();
			
			/* Method-2 */
			Files.copy(file.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isFileUploaded;
	}
}
