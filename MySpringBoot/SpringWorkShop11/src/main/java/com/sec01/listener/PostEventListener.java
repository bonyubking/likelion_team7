package com.sec01.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import com.sec01.event.PostCreatedEvent;

@Component
public class PostEventListener {
	
	@EventListener
	public void handlePost(PostCreatedEvent event) {
		System.out.println("이벤트 리스너 실행됨 :  " + event.getTitle()); 
	}
}
