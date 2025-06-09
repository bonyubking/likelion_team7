package com.sec04;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class LifecycleEventListener {
	
	@EventListener
	public void onStart(ContextRefreshedEvent event) {
		System.out.println("애플리케이션 시작 (ContextRefreshedEvent ) ");
	}
	
	@EventListener
	public void onStop(ContextClosedEvent event) {
		System.out.println("애플리케이션 종료됨 (ContextClosedEvent ) ");
	}
	
	@EventListener
	public void onReady(ApplicationReadyEvent event) {
		System.out.println("모든 초기화 완료 (ApplicationReadyEvent ) ");
	}
	
	@EventListener
	public void onFailed(ApplicationFailedEvent event) {
		System.out.println("애플리케이션 시작 실패 (ApplicationFailedEvent )  " + event.getException().getMessage());
	}
	
	@EventListener
	public void onRequest(RequestHandledEvent event) {
		System.out.println("요청 완료 ! (REquetstHandledEvent ) ");
	}
}
