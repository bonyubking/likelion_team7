package com.sec06;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration 
@EnableAsync 
public class AsyncConfig { 
	
	@Bean
	public Executor asyncExecutor() {
		
		ThreadPoolTaskExecutor my_s = new ThreadPoolTaskExecutor();
		
		my_s.setCorePoolSize(5);
		my_s.setThreadNamePrefix("Async-");
		my_s.initialize();
		return my_s;
	} 
} 
