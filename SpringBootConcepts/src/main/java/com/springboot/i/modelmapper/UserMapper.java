package com.springboot.i.modelmapper;

import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto dtoToEntity(UserEntity user) {
		
		return this.modelMapper.map(user, UserDto.class);
	}
	
	public UserEntity entityToDto(UserDto userDto) {
		
		return this.modelMapper.map(userDto, UserEntity.class);
	}
}
