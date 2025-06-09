package com.sec01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec01.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    
	void deleteByPostId(Long postId);
}
