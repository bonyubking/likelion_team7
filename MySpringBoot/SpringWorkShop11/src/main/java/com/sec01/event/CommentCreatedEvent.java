package com.sec01.event;

import com.sec01.entity.Post;

import lombok.Data;

public @Data class CommentCreatedEvent {
	
	private Long postId;
	private String writer;
	private String content;
	
	public CommentCreatedEvent(Long postId, String writer, String content) {

		this.postId = postId;
		this.writer = writer;
		this.content = content;
	}
	

	

	
	
}
