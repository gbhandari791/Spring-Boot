package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;

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
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = this.postRepo.findAll();
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		List<Post> posts = postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map(post -> postMapper.entityToDto(post)).collect(Collectors.toList());
		
		
		return postDtos;
	}
	
	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
	}

}
