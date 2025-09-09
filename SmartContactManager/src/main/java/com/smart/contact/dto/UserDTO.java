package com.smart.contact.dto;

import java.util.ArrayList;
import java.util.List;

import com.smart.contact.entities.Contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private boolean enabled;
	private String imageUrl;
	private String about;
	List<Contact> contacts = new ArrayList<>();
}
