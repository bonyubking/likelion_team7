package com.sec01.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sec01.event.CommentCreatedEvent;
import com.sec01.event.PostCreatedEvent;

@Component
public class CommentEventListener {
	
	@Async
	@EventListener
	public void handle(CommentCreatedEvent event) throws InterruptedException { 
		Thread.sleep(2000);  
		System.out.println("댓글 이벤트 처리중 .. .by " + 
		event.getWriter()); 
		} 
}
