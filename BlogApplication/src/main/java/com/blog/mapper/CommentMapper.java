package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.Comment;
import com.blog.payloads.CommentDto;
import com.blog.util.DateUtil;

@Component
public class CommentMapper {

	@Autowired
	private ModelMapper mapper;
	
	public CommentDto entityToDto(Comment comment) {
		CommentDto dto = mapper.map(comment, CommentDto.class);
		dto.setCreatedOn(DateUtil.formatDate(comment.getCreatedOn()));
		return dto;
	}
	
	public Comment dtoToEntity(CommentDto dto) {
		return mapper.map(dto, Comment.class);
	}
}
