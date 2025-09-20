package com.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.util.DateUtil;

@Component
public class PostMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public PostDto entityToDto(Post post) {
		
		PostDto dto = this.modelMapper.map(post, PostDto.class);
		dto.setCreatedOn(DateUtil.formatDate(post.getCreatedOn()));
		return dto;
	}
	
	public Post dtoToEntity(PostDto dto) {
		
		return this.modelMapper.map(dto, Post.class);
	}
}
