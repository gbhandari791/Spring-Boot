package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get Jwt token from request		
		String reqToken = request.getHeader("Authorization");
		
		// remove Bearer from token and get valid token
		String username = null;
		String token = null;		
		
		if(reqToken != null && reqToken.startsWith("Bearer")) {
			
			token = reqToken.substring(7);			
			
			try {
				
				username = this.jwtUtil.extractUsernameFromToken(token);
			
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid Jwt");
			}
			
		}
		
		// Once we get the token now validate it
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			if(this.jwtUtil.validateToken(token, username)) {
				
			}
		}
	}

}
