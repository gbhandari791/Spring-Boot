package com.springboot.h.rest.api.file.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Autowired
	FileUploadService uploadService = null;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile( @RequestParam("file") MultipartFile file ){
		
		System.out.println(file.getOriginalFilename());
		uploadService.uploadFile(file);
		return ResponseEntity.ok("File Uploaded Successfully");
	}
}
