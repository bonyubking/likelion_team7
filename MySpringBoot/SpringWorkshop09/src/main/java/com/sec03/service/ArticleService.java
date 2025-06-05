package com.sec03.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec03.entity.Article;
import com.sec03.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;



@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
	
	
	private final ArticleRepository articleRepository;
	
    @Transactional
    public Article createArticle(String title, String content) {
    	Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        return articleRepository.save(article);
    }
}
