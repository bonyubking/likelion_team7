package com.sec06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service 
public class AsyncService { 
	
	@Async
	public void processAsync(String name) {
		System.out.println("비동기 처리중 : " + name
				+ " ( " + Thread.currentThread().getName() + ")");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println("비동기 처리 완료 : " + name);
	}
} 