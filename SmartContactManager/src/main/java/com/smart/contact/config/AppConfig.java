package com.smart.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig  {

	// 1. UserDetailsService
	@Bean
	public UserDetailsService getUserDetailsService() {
		
		return new UserDetailsServiceImpl();
	}
	
	// 2. Password encoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	// 3. Authentication Provider
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(this.getUserDetailsService());
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		
		return authenticationProvider;
	}
	
	// 4. AuthenticationManager (needed if you want to use it manually, e.g., for login services)
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
		
		return security.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(this.authenticationProvider()).build();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(auth -> auth
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
				.anyRequest().permitAll()
				)
		.formLogin(form -> form
				.loginPage("/signin")
				.loginProcessingUrl("/processLogin")
				.defaultSuccessUrl("/user/index", true)
				// .failureUrl("/loginFail")
				)
		.csrf(csrf -> csrf.disable());
		
		return security.build();
	}
	
	
}
