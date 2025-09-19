package com.blog.services.impl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.CategoryMapper;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = this.categoryMapper.dtoToEntity(categoryDto);
		cat.setCreatedOn(System.currentTimeMillis());
		Category savedcat = this.categoryRepo.save(cat);
		return this.categoryMapper.entityToDto(savedcat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		
		Category cat = this.categoryRepo.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Category", "Id", id));
		cat.setTital(categoryDto.getTital());
		cat.setDescription(categoryDto.getDescription());
		cat.setUpdatedOn(System.currentTimeMillis());
		Category uCat = this.categoryRepo.save(cat);
		return this.categoryMapper.entityToDto(uCat);
	}

	@Override
	public CategoryDto getCategory(Integer id) {

		Category cat = this.categoryRepo.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Category", "Id", id));
		return this.categoryMapper.entityToDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map(cat -> categoryMapper.entityToDto(cat)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Integer id) {

		Category cat = this.categoryRepo.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Category", "Id", id));
		this.categoryRepo.delete(cat);		
	}

}
