package com.sec03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service 
public class OrderService { 
		//ApplicationEventPublisher 주입
		//registerUser 메소드에 publishEvent() 호출
	@Autowired  
	private ApplicationEventPublisher publisher; 
	
	
	public void createOrder(String productName, String email) {
		
		//이벤트를 시스템에 알려줌
		publisher.publishEvent(new OrderCreatedEvent(productName, email)); 
		System.out.println("주문 완료 처리중 ..");
	} 
} 