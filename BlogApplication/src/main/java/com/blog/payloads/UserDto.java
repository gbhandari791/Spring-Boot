package com.blog.payloads;

import com.blog.validation.annotation.StrongPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	@JsonProperty(access = Access.READ_ONLY)
	private int id;	
	@NotBlank(message = "First name is required")
	private String firstName;	
	@NotBlank(message = "Last name is required")
	private String lastName;
	@Email(message = "Please provide a valid email address")
	private String email;	
	@JsonProperty(access = Access.WRITE_ONLY)
	@StrongPassword
	private String password;	
	private String about;	
	@JsonProperty(access = Access.READ_ONLY)
	private String createdOn;	
	@JsonIgnore
	private String updatedOn;
}
