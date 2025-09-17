package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.User;
import com.blog.payloads.UserDto;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserDto entityToDto(User user) {

		return this.modelMapper.map(user, UserDto.class);
	}

	public User dtoToEntity(UserDto dto) {

		return this.modelMapper.map(dto, User.class);
	}
}
