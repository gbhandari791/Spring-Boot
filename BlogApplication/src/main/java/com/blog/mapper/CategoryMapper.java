package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.Category;
import com.blog.payloads.CategoryDto;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDto entityToDto(Category category) {
		
		return this.modelMapper.map(category, CategoryDto.class);
	}
	
	public Category dtoToEntity(CategoryDto dto) {
		
		return this.modelMapper.map(dto, Category.class);
	}
}
