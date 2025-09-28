package com.blog.payloads;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer id;
	@NotBlank(message = "Tital is required")
	private String tital;
	private String description;
	private String createdOn;
	@JsonIgnore
	private String updatedOn;
}
