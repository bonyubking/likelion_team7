package com.sec01.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec01.SpringWorkshop10Application;
import com.sec01.entity.Comment;
import com.sec01.entity.Post;
import com.sec01.repository.CommentRepository;
import com.sec01.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // 매 테스트 전 DB 초기화
        commentRepository.deleteAll();
        postRepository.deleteAll();

        // 테스트용으로 기본 게시글 한 개 생성
        Post post = new Post();
        post.setTitle("테스트 게시글");
        post.setContent("내용");
        postRepository.save(post);
    }

    @Test
    void addAndGetAndUpdateAndDeleteComment() throws Exception {
        // 1) 댓글 생성
        Post savedPost = postRepository.findAll().get(0);
        Long postId = savedPost.getId();

        Map<String, String> newComment = new HashMap<>();
        newComment.put("content", "첫 번째 댓글");

        mockMvc.perform(post("/posts/{postId}/comments", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newComment)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.content", is("첫 번째 댓글")))
            .andExpect(jsonPath("$.post.id", is(postId.intValue())));

        // 2) 댓글 목록 조회
        mockMvc.perform(get("/posts/{postId}/comments", postId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].content", is("첫 번째 댓글")));

        // 3) 댓글 수정
        Long commentId = commentRepository.findByPostId(postId).get(0).getId();
        Map<String, String> updated = new HashMap<>();
        updated.put("content", "수정된 댓글");

        mockMvc.perform(put("/posts/{postId}/comments/{commentId}", postId, commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", is("수정된 댓글")));

        // 4) 댓글 삭제
        mockMvc.perform(delete("/posts/{postId}/comments/{commentId}", postId, commentId))
            .andExpect(status().isNoContent());

        // 삭제 후 다시 조회 → 빈 배열
        mockMvc.perform(get("/posts/{postId}/comments", postId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}