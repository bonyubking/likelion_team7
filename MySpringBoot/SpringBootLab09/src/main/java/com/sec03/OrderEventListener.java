package com.sec03;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component 
public class OrderEventListener { 
	
	
	@Order(1)
	@EventListener(condition = "#event.productName.equals('MacBook') ") 
	public void handleMacBook(OrderCreatedEvent event) {
		
		System.out.println("[1] 맥북 주문 알림 전송 : " + event.getProductName()); 
	}
	
	@Order(2)
	@EventListener(condition = "#event.productName.equals('Keyboard') ") 
	public void handleKeyboard(OrderCreatedEvent event) {
		
		System.out.println("[2] 키보드 주문 알림 전송 : " + event.getProductName()); 
	}
	
	@Order(3)
	@EventListener(condition = "#event.productName.equals('notebook') ") 
	public void handlenotebook(OrderCreatedEvent event) {
		
		System.out.println("[3] 노트북 주문 알림 전송 : " + event.getProductName()); 
	} 
	
	
	@Order(4)
	@EventListener(condition = "#event.email.endsWith('.com') ") 
	public void handleComUser(OrderCreatedEvent event) {
		
		System.out.println("[4] .com 도메인 가입자 : " + event.getEmail()); 
	}
	
	@Order(5)
	@EventListener(condition = "#event.email.endsWith('.co.kr') ") 
	public void handleKrUser(OrderCreatedEvent event) {
		
		System.out.println("[5] .co.kr 도메인 가입자 : " + event.getEmail()); 
	} 
}
