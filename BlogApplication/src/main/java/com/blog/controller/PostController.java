package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.PostDto;
import com.blog.payloads.PagedResponse;
import com.blog.payloads.ResponseDto;
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
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable Integer postId){
		
		PostDto post = this.postService.updatePost(dto, postId);
		return ResponseEntity.ok(post);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		
		PostDto post = postService.getPost(postId);
		return ResponseEntity.ok(post);
	}
	
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<PagedResponse<PostDto>> getPostByUser(@PathVariable Integer userId, 
			@RequestParam(name = "page", defaultValue = "1") int pageNumber,
			@RequestParam(name = "size", defaultValue = "5") Integer pageSize){
		
		PagedResponse<PostDto> posts = postService.getPostByUser(userId, pageNumber - 1, pageSize);
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/category/{catId}/post")
	public ResponseEntity<PagedResponse<PostDto>> getPostByCategory(@PathVariable Integer catId, 
			@RequestParam(name = "page", defaultValue = "1") int pageNumber,
			@RequestParam(name = "size", defaultValue = "5") Integer pageSize){
		
		PagedResponse<PostDto> posts = postService.getPostByCategory(catId, pageNumber - 1, pageSize);
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/post")
	public ResponseEntity<PagedResponse<PostDto>> getAllPosts(@RequestParam(name = "page", defaultValue = "1") int pageNumber,
			@RequestParam(name = "size", defaultValue = "5") Integer pageSize) {

		PagedResponse<PostDto> posts = postService.getAllPosts(pageNumber -1 , pageSize);
		return ResponseEntity.ok(posts);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ResponseDto> deletePost(@PathVariable Integer postId){
		
		postService.deletePost(postId);
		return ResponseEntity.ok(new ResponseDto("Post deleted successfully", true));
	}
}
