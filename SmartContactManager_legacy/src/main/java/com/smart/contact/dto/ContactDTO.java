package com.smart.contact.dto;

import com.smart.contact.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

	private int id;
	private String name;
	private String nickName;
	private String work;
	private String email;
	private String phone;
	private String image;
	private String description;
	private User user;
}
