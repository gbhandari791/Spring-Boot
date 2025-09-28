package com.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageDto {

	private int page = 1;
	private int size = 5;
	private String sortBy = "id";
	private String sortOrder = "asc";
	
}
