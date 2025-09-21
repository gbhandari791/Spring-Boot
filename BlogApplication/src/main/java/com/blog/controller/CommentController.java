package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CommentDto;
import com.blog.payloads.PageDto;
import com.blog.payloads.PagedResponse;
import com.blog.payloads.ResponseDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable int userId, @PathVariable int postId, @RequestBody CommentDto commentDto) {
		
		CommentDto comment = commentService.createComment(userId, postId, commentDto);
		return ResponseEntity.ok(comment);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ResponseDto> deleteComment(@PathVariable int commentId){
		
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ResponseDto("Comment deleted successfully", true));
	}
	
	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<PagedResponse<CommentDto>> getAllComments(@PathVariable int postId, PageDto pageDto){
		
		PagedResponse<CommentDto> allCommets = commentService.getAllCommets(postId, pageDto);
		return ResponseEntity.ok(allCommets);
	}
}
