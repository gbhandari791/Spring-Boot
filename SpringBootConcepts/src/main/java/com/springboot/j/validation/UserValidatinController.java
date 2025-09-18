package com.springboot.j.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserValidatinController {

	@PostMapping("/validateUser")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){
		
		return ResponseEntity.ok("User is valid !!");
	}
}
