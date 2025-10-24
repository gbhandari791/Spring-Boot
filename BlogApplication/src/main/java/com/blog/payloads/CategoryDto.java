package com.blog.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;
	@NotBlank(message = "Tital is required")
	private String tital;
	private String description;
	@JsonProperty(access = Access.READ_ONLY)
	private String createdOn;
	@JsonIgnore
	private String updatedOn;
}
