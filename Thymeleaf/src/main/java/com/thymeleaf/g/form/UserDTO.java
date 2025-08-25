package com.thymeleaf.g.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "User Name can not be empty")
	@Size(min =  3, message = "User Name should have min 3 characters")
	private String userName;
	
	@Size(min = 3, max = 8, message = "password shold have mininum 3 and maximum 8 characters")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
