package com.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;	
	private String about;	
	@JsonIgnore
	private Long createdOn;	
	private boolean isDeleted;	
	@JsonInclude(Include.NON_EMPTY)
	private Long deletedOn;
	@JsonIgnore
	private Long updatedOn;
}
