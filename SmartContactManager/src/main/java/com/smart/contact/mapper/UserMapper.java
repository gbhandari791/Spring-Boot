package com.smart.contact.mapper;

import org.springframework.stereotype.Component;

import com.smart.contact.dto.UserDTO;
import com.smart.contact.entities.User;

@Component
public class UserMapper {

	public UserDTO toUserDto(User user) {
		if(user == null) return null;
		
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setEnabled(user.isEnabled());
		dto.setImageUrl(user.getImageUrl());
		dto.setAbout(user.getAbout());
		dto.setContacts(user.getContacts());
		return dto;
	}
	
	public User toUserEntity(UserDTO dto) {
		if(dto == null) return null;
		
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		user.setEnabled(dto.isEnabled());
		user.setImageUrl(dto.getImageUrl());
		user.setAbout(dto.getAbout());
		user.setContacts(dto.getContacts());
		return user;
	}
}
