package com.test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//1 . 핸들러 등록

public class WebConfig implements WebMvcConfigurer{
	
	
	@Autowired
	LoggingInterceptor loggingInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loggingInterceptor);
	}
	
}
