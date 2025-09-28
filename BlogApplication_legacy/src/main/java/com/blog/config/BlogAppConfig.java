package com.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogAppConfig {

	@Bean
	public ModelMapper getModelMapper() {
		
		return new ModelMapper();
	}
}
