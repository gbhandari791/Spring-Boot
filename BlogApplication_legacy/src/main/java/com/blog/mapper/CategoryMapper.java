package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.Category;
import com.blog.payloads.CategoryDto;
import com.blog.util.DateUtil;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDto entityToDto(Category category) {
		
		CategoryDto dto = this.modelMapper.map(category, CategoryDto.class);
		dto.setCreatedOn(DateUtil.formatDate(category.getCreatedOn()));
		return dto;
	}
	
	public Category dtoToEntity(CategoryDto dto) {
		
		return this.modelMapper.map(dto, Category.class);
	}
}
