package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	
	CategoryDto getCategory(Integer id);
	
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(Integer id);
	
}
