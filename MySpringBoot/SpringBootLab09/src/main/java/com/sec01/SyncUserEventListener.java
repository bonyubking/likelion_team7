package com.sec01;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component 
public class SyncUserEventListener { 
	
	@EventListener 
	public void handleUserRegistered(UserRegisteredEvent event) { 
		System.out.println("환영 메시지 전송: " + event.getUsername()); 
	} 
}
