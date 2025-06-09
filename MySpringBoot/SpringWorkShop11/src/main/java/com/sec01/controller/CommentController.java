  package com.sec01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec01.entity.Comment;
import com.sec01.entity.Post;
import com.sec01.service.CommentService;
import com.sec01.service.PostService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/{postId}")
	public Comment create(@PathVariable Long postId, @RequestBody Comment comment) {
		
		
        return commentservice.addComment(postId, comment.getWriter(), comment.getContent());
		
	}

}
