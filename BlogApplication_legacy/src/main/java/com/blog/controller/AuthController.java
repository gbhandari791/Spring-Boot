package com.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.UserDto;
import com.blog.security.JwtAuthRequest;
import com.blog.security.JwtAuthResponce;
import com.blog.security.JwtUtil;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> login(@RequestBody JwtAuthRequest authRequest){
		authenticate(authRequest.getUsername(), authRequest.getPassword());
		String token = this.jwtUtil.generateToken(authRequest.getUsername());
		JwtAuthResponce authResponce = new JwtAuthResponce();
		authResponce.setToken(token);
		
		return ResponseEntity.ok(authResponce);
	}
	
	private void authenticate(String username, String password) {	
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		authenticationManager.authenticate(authenticationToken);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		
		UserDto createdUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
}
