package com.blog.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.payloads.PostDto;
import com.blog.payloads.PageDto;
import com.blog.payloads.PagedResponse;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto dto, Integer postId);
	
	PostDto getPost(Integer postId);
	
	PagedResponse<PostDto> getAllPosts(PageDto page);
	
	void deletePost(Integer postId);
	
	PagedResponse<PostDto> getPostByUser(Integer userId, PageDto page);
	
	PagedResponse<PostDto> getPostByCategory(Integer categoryId, PageDto page);
	
	PagedResponse<PostDto> searchByTital(String search, PageDto page);
	
	boolean uploadPostImage(Integer postId, MultipartFile file);
}
