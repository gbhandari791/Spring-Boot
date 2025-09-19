package com.springboot.j.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserValidatinController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/validateUser")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){
		
		userService.registerUser(userDto.getRole(), userDto.getAbout());
		return ResponseEntity.ok("User is valid !!");
	}
}
