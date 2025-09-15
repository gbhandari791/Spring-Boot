package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = dtoToEntity(userDto);
		User savedUser = this.userRepo.save(user);
		
		return entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		
		
		
		return null;
	}

	@Override
	public UserDto findUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	private User dtoToEntity(UserDto dto) {
		
		User user = new User();
		user.setId(dto.getId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getFirstName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		user.setCreatedOn(dto.getCreatedOn());
		user.setDeleted(dto.isDeleted());
		user.setDeletedOn(dto.getDeletedOn());
		
		return user;
	}
	
	private UserDto entityToDto(User entity) {
		
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getFirstName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setAbout(entity.getAbout());
		dto.setCreatedOn(entity.getCreatedOn());
		dto.setDeleted(entity.isDeleted());
		dto.setDeletedOn(entity.getDeletedOn());
		
		return dto;
	}

}
