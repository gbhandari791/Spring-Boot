package com.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> resourceNotFoundEceptionHandeler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ResponseDto dto = new ResponseDto(message, false);
		
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.NOT_FOUND);
	}
	
}
