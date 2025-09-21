package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.mapper.CommentMapper;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PageDto;
import com.blog.payloads.PagedResponse;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.CommentService;
import com.blog.util.GeneralUtil;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userReo;
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public CommentDto createComment(int userId, int postId, CommentDto dto) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		User user = userReo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Comment comment = commentMapper.dtoToEntity(dto);
		comment.setPost(post);
		comment.setUser(user);
		Comment save = commentRepo.save(comment);
		return commentMapper.entityToDto(save);
	}

	@Override
	public void deleteComment(int commentId) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		commentRepo.delete(comment);
		
	}

	@Override
	public PagedResponse<CommentDto> getAllCommets(int postId, PageDto pageDto) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		Pageable pageable = PageRequest.of(pageDto.getPage() - 1, pageDto.getSize());
		
		Page<Comment> byPost = commentRepo.findByPost(post, pageable);
		List<Comment> comments = byPost.getContent();
		List<CommentDto> commentDto = comments.stream().map(comment -> commentMapper.entityToDto(comment)).collect(Collectors.toList());	
		
		return GeneralUtil.createPagedResponse(commentDto, byPost);
	}

	
}
