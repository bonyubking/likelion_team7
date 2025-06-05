package com.sec01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec01.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
