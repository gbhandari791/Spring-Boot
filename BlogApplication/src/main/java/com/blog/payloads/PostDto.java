package com.blog.payloads;

import com.blog.entities.Category;
import com.blog.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer id;
	@NotBlank(message = "Tital is required")
	private String tital;
	private String content;
	private String imageName;
	@JsonIgnore
	private long createdOn;
	@JsonIgnore
	private long updatedOn;
	
	private UserDto user;
	
	private CategoryDto category;
}
