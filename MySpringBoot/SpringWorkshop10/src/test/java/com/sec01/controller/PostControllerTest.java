package com.sec01.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec01.entity.Post;
import com.sec01.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // 매 테스트마다 DB 초기화
        postRepository.deleteAll();
    }

    @Test
    void createAndGetAllAndUpdateAndDelete() throws Exception {
        // 1) CREATE: 게시글 하나 등록
        Map<String, String> newPost = new HashMap<>();
        newPost.put("title", "첫 번째 게시글");
        newPost.put("content", "내용 A");

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPost)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.title").value("첫 번째 게시글"))
            .andExpect(jsonPath("$.content").value("내용 A"));

        // 2) READ ALL: 방금 등록한 게시글이 리스트에 있는지 확인
        mockMvc.perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("첫 번째 게시글")));

        // 3) READ ONE: ID = 1번 게시글 조회 (존재해야 함)
        Post saved = postRepository.findAll().get(0); // DB에서 방금 저장된 엔티티 받아옴
        Long savedId = saved.getId();

        mockMvc.perform(get("/posts/{id}", savedId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(savedId))
            .andExpect(jsonPath("$.title").value("첫 번째 게시글"))
            .andExpect(jsonPath("$.content").value("내용 A"));

        // 4) UPDATE: 제목과 내용을 수정
        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("title", "수정된 게시글 제목");
        updatedData.put("content", "수정된 내용");

        mockMvc.perform(put("/posts/{id}", savedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedData)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(savedId))
            .andExpect(jsonPath("$.title").value("수정된 게시글 제목"))
            .andExpect(jsonPath("$.content").value("수정된 내용"));

        // 5) DELETE: 해당 ID 게시글 삭제
        mockMvc.perform(delete("/posts/{id}", savedId))
            .andExpect(status().isNoContent());

        // 삭제 후, 다시 조회 시 404 Not Found
        mockMvc.perform(get("/posts/{id}", savedId))
            .andExpect(status().isNotFound());
    }
}