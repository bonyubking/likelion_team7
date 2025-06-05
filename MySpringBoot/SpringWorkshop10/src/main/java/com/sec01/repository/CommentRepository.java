package com.sec01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec01.entity.Comment;
import com.sec01.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByPostId(Long postId);

}
