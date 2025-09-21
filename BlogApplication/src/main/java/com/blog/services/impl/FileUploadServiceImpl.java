package com.blog.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blog.exceptions.CustomException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.services.FileUploadService;
import com.blog.util.GeneralUtil;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Value("${image.upload-dir}")
	private String imageDirPath;
	@Value("${image.uploaded-dir}")
	private String imageUploadedPath;
	
	@Override
	public boolean uploadFile(String path, String fileName, MultipartFile file) throws IOException {
		
		makeDir(path);
		
		Path filePath = Paths.get(path, fileName);
		
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		return Files.exists(filePath);
	}

	public String uploadImage(MultipartFile file) {

		String fileName = file.getOriginalFilename();
		if (!(fileName.toLowerCase().endsWith("jpg") || fileName.toLowerCase().endsWith("jpeg"))) {
			throw new CustomException("Invalid file extension. Allowed: jpg, jpeg");
		}

		fileName = appendCurrTimeToFileName(fileName);
		try {
			if (uploadFile(imageDirPath, fileName, file))
				return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	private String appendCurrTimeToFileName(String fileName) {
		if (fileName == null || fileName.isBlank())
			return null;

		int dotIndex = fileName.lastIndexOf(".");
		String name;
		String extension = "";

		if (dotIndex > 0) {
			name = fileName.substring(0, dotIndex);
			extension = fileName.substring(dotIndex);
		} else {
			name = fileName; // no extension
		}

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

		return  GeneralUtil.concat(name, "_", timestamp, extension);
	}
	
	private static void makeDir(String path) {
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}

	@Override
	public String getFileUrl(String dirPath, String uploadedPath,String fileName) {
		
		if(fileName == null) return null;
		
		Path p = Paths.get(dirPath, fileName);
		if(!Files.exists(p)) return null;
		
		return ServletUriComponentsBuilder.fromCurrentContextPath()
		.path(uploadedPath).path(fileName).toUriString();
	}

	@Override
	public String getImageUrl(String fileName) {

		return getFileUrl(imageDirPath, imageUploadedPath ,fileName);
	}

}
