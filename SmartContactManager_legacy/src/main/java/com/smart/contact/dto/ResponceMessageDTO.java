package com.smart.contact.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponceMessageDTO {

	private String message;
	private String type;
	
	public ResponceMessageDTO(String message, String type) {
		super();
		this.message = message;
		this.type = type;
	}
	
	
}
