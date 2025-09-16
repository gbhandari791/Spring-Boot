package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = dtoToEntity(userDto);
		user.setCreatedOn(System.currentTimeMillis());
		User savedUser = this.userRepo.save(user);
		
		return entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setUpdatedOn(System.currentTimeMillis());

		this.userRepo.save(user);

		return entityToDto(user);
	}

	@Override
	public UserDto findUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return entityToDto(user);
	}

	@Override
	public List<UserDto> findAllUsers() {
	
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDto = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		this.userRepo.delete(user);

	}
	
	private User dtoToEntity(UserDto dto) {
		
		User user = new User();
		user.setId(dto.getId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		user.setCreatedOn(dto.getCreatedOn());
		user.setDeleted(dto.isDeleted());
		user.setDeletedOn(dto.getDeletedOn());
		user.setUpdatedOn(dto.getUpdatedOn());
		
		return user;
	}
	
	private UserDto entityToDto(User entity) {
		
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setAbout(entity.getAbout());
		dto.setCreatedOn(entity.getCreatedOn());
		dto.setDeleted(entity.isDeleted());
		dto.setDeletedOn(entity.getDeletedOn());
		dto.setUpdatedOn(entity.getUpdatedOn());
		
		return dto;
	}

}
