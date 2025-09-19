package com.blog.payloads;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer id;
	private String tital;
	private String content;
	private String imageName;
	private String createdOn;
	private String updatedOn;
	private boolean isDeleted;
	private String deletedOn;
	
	private User user;
	
	private Category category;
}
