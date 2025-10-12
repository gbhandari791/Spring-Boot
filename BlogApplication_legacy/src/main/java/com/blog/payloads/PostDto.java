package com.blog.payloads;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	@JsonInclude(Include.NON_EMPTY)
	private String image;
	private String createdOn;
	@JsonIgnore
	private String updatedOn;
	
	private UserDto user;
	
	private CategoryDto category;
}
