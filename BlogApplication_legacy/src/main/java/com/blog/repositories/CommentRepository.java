package com.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Comment;
import com.blog.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer>   {

	Page<Comment> findByPost(Post post, Pageable pageable);
}
