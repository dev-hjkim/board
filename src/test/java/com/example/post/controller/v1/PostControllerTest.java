package com.example.post.controller.v1;

import com.example.common.util.JwtUtil;
import com.example.post.dto.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    private MockMvc mvc;
    private ObjectMapper objectMapper;
    String accessToken;

    @Autowired
    public void setPostControllerTest(MockMvc mvc, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
        this.accessToken = jwtUtil.generate("5", "ACCESS");
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() throws Exception {
        mvc.perform(get("/v1/board/AAA/posts")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() throws Exception {
        mvc.perform(get("/v1/board/AAA/posts/13")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() throws Exception {
        mvc.perform(delete("/v1/board/AAA/posts/13")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("createPost :: 정상 케이스")
    void createPost() throws Exception {
        PostRequest postRequest = new PostRequest("test14", "test14's content");

        String content = objectMapper.writeValueAsString(postRequest);

        mvc.perform(post("/v1/board/AAA/posts")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("updatePost :: 정상 케이스")
    void updatePost() throws Exception {
        PostRequest postRequest = new PostRequest("test13", "test13's modified content");

        String content = objectMapper.writeValueAsString(postRequest);

        mvc.perform(put("/v1/board/AAA/posts/13")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

}