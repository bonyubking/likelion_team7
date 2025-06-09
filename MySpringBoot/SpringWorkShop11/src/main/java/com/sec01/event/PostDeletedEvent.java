package com.sec01.event;

import com.sec01.entity.Post;

import lombok.Data;

public @Data class PostDeletedEvent {
	
	private Long postId;

	
	public PostDeletedEvent(Long postId) {

		this.postId = postId;

	}


	
	
}
