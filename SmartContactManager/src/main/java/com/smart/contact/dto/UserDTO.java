package com.smart.contact.dto;

import java.util.ArrayList;
import java.util.List;

import com.smart.contact.entities.Contact;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int id;
	@NotBlank(message = "Name should not be blank")
	private String name;
	@NotBlank(message = "Email should not be blank")
	@Email(message = "Please provide a valid email address")
	private String email;
	@NotBlank(message = "Password should not be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	private String role;
	private boolean enabled;
	private String imageUrl;
	private String about;
	@AssertTrue(message = "You must agree to the terms and conditions")
	private boolean agreement;
	List<Contact> contacts = new ArrayList<>();
}
