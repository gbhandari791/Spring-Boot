package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.security.JwtAuthRequest;
import com.blog.security.JwtAuthResponce;
import com.blog.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
}
