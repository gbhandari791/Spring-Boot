package com.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
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
	@JsonIgnore
	private long createdOn;
	@JsonIgnore
	private long updatedOn;
}
