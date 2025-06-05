package com.test01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {
	
	@PersistenceContext // 현재 트랜잭션과 관련된 엔티티 매니저를 프록시
	private EntityManager em; // JPA crud등의 메소드 실행을 주관하는 매니저
	
	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
	}
}
