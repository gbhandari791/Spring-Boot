package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.util.DateUtil;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserDto entityToDto(User user) {

		 UserDto dto = this.modelMapper.map(user, UserDto.class);
		 dto.setCreatedOn(DateUtil.formatDate(user.getCreatedOn()));
		 return dto;
	}

	public User dtoToEntity(UserDto dto) {

		return this.modelMapper.map(dto, User.class);
	}
}
