package com.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;	
	private String firstName;	
	private String lastName;	
	private String email;	
	private String password;	
	private String about;	
	private Long createdOn;	
	private boolean isDeleted;	
	private Long deletedOn;
	private Long updatedOn;
}
