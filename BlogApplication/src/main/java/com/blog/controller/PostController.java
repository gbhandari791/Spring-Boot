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
import org.springframework.web.multipart.MultipartFile;

import com.blog.payloads.PostDto;
import com.blog.payloads.PageDto;
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
	public ResponseEntity<PagedResponse<PostDto>> getPostByUser(@PathVariable Integer userId, PageDto page){
		
		PagedResponse<PostDto> posts = postService.getPostByUser(userId, page);
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/category/{catId}/post")
	public ResponseEntity<PagedResponse<PostDto>> getPostByCategory(@PathVariable Integer catId, PageDto page){
		
		PagedResponse<PostDto> posts = postService.getPostByCategory(catId, page);
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/post")
	public ResponseEntity<PagedResponse<PostDto>> getAllPosts(PageDto page) {

		PagedResponse<PostDto> posts = postService.getAllPosts(page);
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/post/search/{search}")
	public ResponseEntity<PagedResponse<PostDto>> searchPost(@PathVariable String search, PageDto page){
		
		PagedResponse<PostDto> posts = this.postService.searchByTital(search, page);
		return ResponseEntity.ok(posts);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ResponseDto> deletePost(@PathVariable Integer postId){
		
		postService.deletePost(postId);
		return ResponseEntity.ok(new ResponseDto("Post deleted successfully", true));
	}
	
	@PutMapping("/post/{postId}/image/upload")
	public ResponseEntity<ResponseDto> uploadPostImage(@PathVariable int postId, @RequestParam(name = "image", required = true) MultipartFile file){
		
		boolean imageUploaded = this.postService.uploadPostImage(postId, file);
		ResponseDto response = new ResponseDto();
		ResponseEntity<ResponseDto> entity = null;
		if(imageUploaded) {
			response.setMessage("Image uploaded successfully");
			response.setSuccess(true);
			entity = ResponseEntity.ok(response);
		} else {
			response.setMessage("Something went wrong: Falied to upload the image");
			response.setSuccess(false);
			entity = ResponseEntity.internalServerError().body(response);
		}
		return entity;
	}
}
