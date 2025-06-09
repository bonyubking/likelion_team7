package com.sec01.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import com.sec01.event.PostCreatedEvent;
import com.sec01.event.PostDeletedEvent;
import com.sec01.repository.CommentRepository;

@Component
public class PostDeletedEventListener {
	
	private final CommentRepository commentRepository;
	
    public PostDeletedEventListener(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
	
	@EventListener
	public void onPostDeleted(PostDeletedEvent event) { 
		System.out.println("해당 게시글의 댓글 삭제 실행: postId = " + 
		event.getPostId()); 
		commentRepository.deleteByPostId(event.getPostId()); 
}
}
