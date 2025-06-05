// src/main/java/com/sec01/controller/PostController.java
package com.sec01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sec01.entity.Post;
import com.sec01.repository.PostRepository;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /** 1) 게시글 생성 **/
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        // title 필드가 null 이면 예외 발생 (DB 제약에 의해)
        Post saved = postRepository.save(post);
        return ResponseEntity.ok(saved);
    }

    /** 2) 모든 게시글 조회  **/
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> list = postRepository.findAll();
        return ResponseEntity.ok(list);
    }

    /** 3) 단일 게시글 조회 **/
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> opt = postRepository.findById(id);
        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 4) 게시글 업데이트 **/
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestBody Post updatedPost) {

        return postRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedPost.getTitle());
                    existing.setContent(updatedPost.getContent());
                    Post saved = postRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 5) 게시글 삭제 **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(existing -> {
                    postRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}