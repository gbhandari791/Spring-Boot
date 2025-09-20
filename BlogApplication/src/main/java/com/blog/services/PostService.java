package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDto;
import com.blog.payloads.PagedResponse;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto dto, Integer postId);
	
	PostDto getPost(Integer postId);
	
	PagedResponse<PostDto> getAllPosts(int pageNumber, int pageSize);
	
	void deletePost(Integer postId);
	
	PagedResponse<PostDto> getPostByUser(Integer userId, int pageNumber, int pageSize);
	
	PagedResponse<PostDto> getPostByCategory(Integer categoryId, int pageNumber, int pageSize);
}
