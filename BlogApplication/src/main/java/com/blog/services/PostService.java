package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto dto, Integer postId);
	
	PostDto getPost(Integer postId);
	
	List<PostDto> getAllPosts();
	
	void deletePost(Integer postId);
}
