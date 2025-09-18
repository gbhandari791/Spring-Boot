package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.UserMapper;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = userMapper.dtoToEntity(userDto);
		user.setCreatedOn(System.currentTimeMillis());
		User savedUser = this.userRepo.save(user);
		
		return userMapper.entityToDto(savedUser);
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

		User uUser = this.userRepo.save(user);

		return userMapper.entityToDto(uUser);
	}

	@Override
	public UserDto findUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return userMapper.entityToDto(user);
	}

	@Override
	public List<UserDto> findAllUsers() {
	
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDto = users.stream().map(user -> userMapper.entityToDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		this.userRepo.delete(user);

	}

}
