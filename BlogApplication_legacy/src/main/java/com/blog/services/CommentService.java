package com.blog.services;

import com.blog.payloads.CommentDto;
import com.blog.payloads.PageDto;
import com.blog.payloads.PagedResponse;

public interface CommentService {

	CommentDto createComment(int userId, int postId, CommentDto dto);
	
	void deleteComment(int commentId);
	
	PagedResponse<CommentDto> getAllCommets(int postId, PageDto pageDto);
}
