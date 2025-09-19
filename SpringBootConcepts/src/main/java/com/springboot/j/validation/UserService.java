package com.springboot.j.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Service
@Validated
public class UserService {

	public void registerUser(@NotBlank(message = "Role must not be blank") 
		String role, @Size(min = 3, message = "Aboult should have atleast 3 charaters") String about) {
		
		System.out.println("User is Valid!");
	}
}
