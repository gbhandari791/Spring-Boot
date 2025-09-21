package com.blog.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	boolean uploadFile(String path, String fileNam, MultipartFile file) throws IOException;
	
	String uploadImage(MultipartFile file);
	
	String getFileUrl(String dirPath, String uploadedPath,String fileName);
	
	String getImageUrl(String fileName);
}
