package com.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagedResponse<T> {

	private List<T> content;
	private int page;
	private int size;
	private int totalPages;
	private long totalElements;
	private boolean last;
}
