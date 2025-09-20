package com.blog.payloads;

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
	private String title;
	private String content;
	private String imageName;
	private String createdOn;
	@JsonIgnore
	private long updatedOn;
	
	private UserDto user;
	
	private CategoryDto category;
}
