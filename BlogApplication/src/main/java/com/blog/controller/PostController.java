package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.PostDto;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{catId}/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto, @PathVariable("userId") Integer userId,
			@PathVariable("catId") Integer catId ){
		
		PostDto post = postService.createPost(dto, userId, catId);
		
		return ResponseEntity.ok(post);
		
	}
}
