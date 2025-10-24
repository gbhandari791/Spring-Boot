package com.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.constants.AppConstant;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exceptions.CustomException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.UserMapper;
import com.blog.payloads.UserDto;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import com.blog.util.GeneralUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		boolean present = userRepo.findByEmail(userDto.getEmail()).isPresent();
		if(present) {
			throw new CustomException(GeneralUtil.concat("User with email ", userDto.getEmail(), " already exists"));
		}
		User user = userMapper.dtoToEntity(userDto);
		Role role = this.roleRepo.findById(AppConstant.ID_ROLE_NORMAL).get();
		user.setRoles(Set.of(role));		
		
		User savedUser = this.userRepo.save(user);
		
		return userMapper.entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
				

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Optional<User> byEmail = userRepo.findByEmail(userDto.getEmail());
		if(byEmail.isPresent()) {
			if(user.getId() != byEmail.get().getId()) {
				throw new CustomException(GeneralUtil.concat("User with email ", userDto.getEmail(), " already exists"));
			}
		}

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

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
