package com.blog.util;

import java.util.List;

import org.springframework.data.domain.Page;

import com.blog.payloads.PagedResponse;

public class GeneralUtil {

	 public static <T> PagedResponse<T> createPagedResponse(List<T> content, Page<?> page) {
		 
	        PagedResponse<T> response = new PagedResponse<>();
	        response.setContent(content);
	        response.setPage(page.getNumber() + 1);
	        response.setSize(page.getSize());
	        response.setTotalPages(page.getTotalPages());
	        response.setTotalElements(page.getTotalElements());
	        response.setLast(page.isLast());
	        return response;
	    }
}
