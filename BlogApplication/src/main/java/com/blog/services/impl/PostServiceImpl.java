package com.blog.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.CustomException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payloads.PostDto;
import com.blog.payloads.PagedResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import com.blog.util.GeneralUtil;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private PostMapper postMapper;
	
	Set<String> ALLOWED_SORTED_FIELDS = Set.of("tital", "createdOn");
	
	@Override
	public PostDto createPost(PostDto dto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post postEntity = postMapper.dtoToEntity(dto);
		if(dto.getImageName() != null && !dto.getImageName().trim().isEmpty()) {
			postEntity.setImageName(dto.getImageName());
		} else {
			postEntity.setImageName("default.img");
		}
		postEntity.setCreatedOn(System.currentTimeMillis());
		postEntity.setUser(user);
		postEntity.setCategory(cat);
		
		Post dbPost = postRepo.save(postEntity);
		
		return postMapper.entityToDto(dbPost);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTital(dto.getTital());
		post.setContent(dto.getContent());
		if(post.getImageName() != null && !post.getImageName().trim().isEmpty()) {
			post.setImageName(dto.getImageName());
		}
		post.setUpdatedOn(System.currentTimeMillis());
		
		Post dbPost = postRepo.save(post);
		return postMapper.entityToDto(dbPost);
	}

	@Override
	public PostDto getPost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return this.postMapper.entityToDto(post);
	}

	@Override
	public PagedResponse<PostDto> getAllPosts(int pageNumber, int pageSize, String sortBy, String sortOrder) {
		
		if(!ALLOWED_SORTED_FIELDS.contains(sortOrder)) {
			throw new CustomException("Invalid sort field : " + sortBy);
		}
		
		Sort sort = "desc".equalsIgnoreCase(sortOrder) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> postPage = this.postRepo.findAll(page);
		List<Post> posts = postPage.getContent();
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}

	@Override
	public PagedResponse<PostDto> getPostByUser(Integer userId, int pageNumber, int pageSize) {
						
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Pageable pageAble = PageRequest.of(pageNumber, pageSize);		
		Page<Post> postPage = postRepo.findByUser(user, pageAble);
		List<Post> posts = postPage.getContent();
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}

	@Override
	public PagedResponse<PostDto> getPostByCategory(Integer categoryId, int pageNumber, int pageSize) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Pageable page = PageRequest.of(pageNumber, pageSize);		
		Page<Post> postPage = postRepo.findByCategory(cat, page);
		List<Post> posts = postPage.getContent();		
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		
		return GeneralUtil.createPagedResponse(postDtos, postPage);
	}
	
	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
	}
	
	
}
