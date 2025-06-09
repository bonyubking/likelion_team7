package com.sec01.event;

import com.sec01.entity.Post;

import lombok.Data;

public @Data class PostCreatedEvent {
	
	private Long postId;
	private String title;
	
	public PostCreatedEvent(Long postId, String title) {

		this.postId = postId;
		this.title = title;
	}


	
	
}
