package com.blog.payloads;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {

	private String message;
	private boolean success;
	@JsonInclude(Include.NON_EMPTY)
	private List<FieldErrorDto> errors;
	
	public ResponseDto(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
}
