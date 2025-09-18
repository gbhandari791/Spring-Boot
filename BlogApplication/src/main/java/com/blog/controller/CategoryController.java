package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.ResponseDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto category = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto, 
				@PathVariable("catId") Integer id ){
		
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, id);
		return ResponseEntity.ok(updateCategory);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer id){
				
		return ResponseEntity.ok(this.categoryService.getCategory(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ResponseDto> deleteCategory(@PathVariable("catId") Integer id){
		this.categoryService.deleteCategory(id);
		return ResponseEntity.ok(new ResponseDto("Category deleted successfully!", true));
	}
	
}
