package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto findUserById(Integer userId);
	
	List<UserDto> findAllUsers();
	
	void deleteUser(Integer userId);
}
