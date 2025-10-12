package com.blog.exceptions;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.FieldErrorDto;
import com.blog.payloads.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> resourceNotFoundEceptionHandeler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ResponseDto dto = new ResponseDto(message, false);
		
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> handleValidationError(MethodArgumentNotValidException ex){
		
		List<FieldErrorDto> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach( error -> 
			errors.add(new FieldErrorDto(error.getField(), error.getDefaultMessage())) );
		
		ResponseDto responseDto = new ResponseDto("Validation failed", false, errors);
		return ResponseEntity.badRequest().body(responseDto);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> fallbackExceptionHandler(Exception ex){
		
		ResponseDto dto = new ResponseDto("Something went wrong : " + ex.getMessage(), false);		
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseDto> handleCustomExeption(CustomException ex) {
		
		ResponseDto responseDto = new ResponseDto(ex.getMessage(), false);
		return ResponseEntity.badRequest().body(responseDto);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ResponseDto> handelBadCredentialsException(BadCredentialsException ex){
		
		ResponseDto responseDto = new ResponseDto("Invalid username or password", false);
		return 	new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
		
	}
	
}
