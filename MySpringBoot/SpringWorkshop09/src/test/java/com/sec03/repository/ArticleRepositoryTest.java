package com.sec03.repository;

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
import com.sec03.entity.Article;

@DataJpaTest 
@ContextConfiguration(classes=SpringWorkshop09Application.class)
@EnableJpaRepositories(basePackages = "com.sec03.repository")
@EntityScan(basePackages = "com.sec03.entity")
class ArticleRepositoryTest { 
 
	@Autowired 
	private ArticleRepository articleRepository; 
	 
	@Test 
	void testSaveAndFindById() { 

		Article article = new Article(); 
		article.setTitle("test post"); 
		article.setContent("test content"); 
		article.setCreatedDate(LocalDateTime.now()); 
	 
	 
	    Article savedarticle = articleRepository.save(article); 
	    Optional<Article> foundMember = 
	    			articleRepository.findById(savedarticle.getId()); 
	 
	
	    assertThat(foundMember).isPresent(); 
		assertThat(foundMember.get().getTitle()).isEqualTo("test post"); 
		assertThat(foundMember.get().getContent()).isEqualTo("test content"); 
	} 
}