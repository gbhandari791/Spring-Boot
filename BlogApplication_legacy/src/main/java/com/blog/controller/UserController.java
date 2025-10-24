package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ResponseDto;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@PostMapping("/")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
//		
//		UserDto createdUser = this.userService.createUser(userDto);
//		
//		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updateUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseDto> deleteUser(@PathVariable("userId") Integer userId){
		
		this.userService.deleteUser(userId);
		ResponseDto responseDto = new ResponseDto("User deleted successfully", true);
		return ResponseEntity.ok(responseDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		return  ResponseEntity.ok(this.userService.findAllUsers()) ;
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getAllUsers(@PathVariable("userId") Integer userId) {
		
		return  ResponseEntity.ok(this.userService.findUserById(userId));
	}
}
