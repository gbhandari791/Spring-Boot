package com.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;
	@NotBlank(message = "Tital is required")
	private String title;
	private String content;
	@JsonInclude(Include.NON_EMPTY)
	private String image;
	@JsonProperty(access = Access.READ_ONLY)
	private String createdOn;
	@JsonIgnore
	private String updatedOn;
	
	private UserDto user;
	
	private CategoryDto category;
}
