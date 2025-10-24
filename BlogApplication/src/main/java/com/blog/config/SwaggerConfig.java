package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenApi() {

		final String securitySchemeName = "bearerAuth";

		return new OpenAPI()
				.info(new Info()
						.title("Blog Apllication API")
						.description("Spring Boot REST API with JWT and Swagger")
						.version("1.0.0"))
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components()
						.addSecuritySchemes(securitySchemeName, 
								new SecurityScheme()
									.name(securitySchemeName)
									.type(Type.HTTP)
									.scheme("bearer")
									.bearerFormat("JWT").in(In.HEADER)
									.description("Enter JWT token here (without 'Bearer ')")));
	}
}
