package com.sec03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec03.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
