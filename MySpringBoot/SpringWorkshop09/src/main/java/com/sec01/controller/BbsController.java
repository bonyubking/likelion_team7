package com.sec01.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bbs")
public class BbsController {


    @GetMapping
    public ResponseEntity<String> getAllArticles() {
        System.out.println("전체 글보기 호출");
        return ResponseEntity.ok("전체 글 목록 조회 창");
    }


    @GetMapping("/{articleId}")
    public ResponseEntity<String> getArticleById(@PathVariable Long articleId) {
        System.out.println("글 상세보기 호출: " + articleId);
        return ResponseEntity.ok("글 상세 조회 창 " + articleId);
    }


    @PostMapping
    public ResponseEntity<String> createArticle() {
        System.out.println("글쓰기 호출");
        return ResponseEntity.ok("글쓰기 창");
    }


    @PutMapping("/{articleId}")
    public ResponseEntity<String> updateArticle(@PathVariable Long articleId) {
        System.out.println("글수정 호출: " + articleId);
        return ResponseEntity.ok("글 수정 창 " + articleId);
    }


    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId) {
        System.out.println("글삭제 호출 " + articleId);
        return ResponseEntity.ok("글 삭제 창: " + articleId);
    }
}
