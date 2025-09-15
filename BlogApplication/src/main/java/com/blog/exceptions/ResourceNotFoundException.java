package com.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String recourceName;
	String fieldName;
	Integer fieldValue;
	
	public ResourceNotFoundException(String recourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with %s : %l", recourceName, fieldName, fieldValue ));
		this.recourceName = recourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
