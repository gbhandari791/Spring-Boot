package com.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable pageable);
	
	Page<Post> findByCategory(Category category, Pageable pageable);
	
	Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
