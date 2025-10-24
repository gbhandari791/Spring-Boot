package com.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	private final String[] SWAGGER_URLS = {"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"};
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl) {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,  JwtAuthenticationFilter authFilter ,
			JwtAuthenticationEntryPoint authEntryPoint, JwtAccessDeniedHandler accessDeniedHandler, UserDetailsServiceImpl userDetailsServiceImpl ) throws Exception {
		
		http
			.csrf( csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling( ex -> ex
					.authenticationEntryPoint(authEntryPoint)
					.accessDeniedHandler(accessDeniedHandler)					
			)
			.authorizeHttpRequests( auth -> auth
			
					.requestMatchers("/api/v1/auth/**").permitAll()
					// .requestMatchers(HttpMethod.GET, "/api/user/").hasRole("ADMIN")
					.requestMatchers(this.SWAGGER_URLS).permitAll()
					.anyRequest().authenticated()
			);
		
		http.authenticationProvider(this.authenticationProvider(userDetailsServiceImpl));
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
}
