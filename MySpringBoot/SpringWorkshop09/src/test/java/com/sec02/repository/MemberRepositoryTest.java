package com.sec02.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import com.sec01.SpringWorkshop09Application;
import com.sec02.entity.Member;

@DataJpaTest 
@ContextConfiguration(classes=SpringWorkshop09Application.class)
@EnableJpaRepositories(basePackages = "com.sec02.repository")
@EntityScan(basePackages = "com.sec02.entity")
class MemberRepositoryTest { 
 
	@Autowired 
	private MemberRepository memberRepository; 
	 
	@Test 
	void testSaveAndFindById() { 

	    Member member = new Member(); 
	    member.setUsername("testuser"); 
	    member.setEmail("testuser@example.com"); 
	    member.setCreatedDate(LocalDateTime.now()); 
	 
	 
	    Member savedMember = memberRepository.save(member); 
	    Optional<Member> foundMember = 
	    			memberRepository.findById(savedMember.getId()); 
	 
	
	    assertThat(foundMember).isPresent(); 
		assertThat(foundMember.get().getUsername()).isEqualTo("testuser"); 
		assertThat(foundMember.get().getEmail()).isEqualTo("testuser@example.com"); 
	} 
}