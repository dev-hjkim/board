package com.example.comment.controller.v1;

import com.example.comment.dto.CommentRequest;
import com.example.common.util.JwtUtil;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    private MockMvc mvc;
    private ObjectMapper objectMapper;
    String accessToken;

    @Autowired
    public void setPostControllerTest(MockMvc mvc, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
        this.accessToken = jwtUtil.generate("7", "ACCESS");
    }

    @Test
    @DisplayName("getCommentList :: 정상 케이스")
    void getCommentList() throws Exception {
        mvc.perform(get("/v1/board/AAA/posts/1/comments")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("deleteComment :: 정상 케이스")
    void deleteComment() throws Exception {
        mvc.perform(delete("/v1/board/AAA/posts/1/comments/1")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("createComment :: 정상 케이스")
    void createComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContent("test1's new comment");

        String content = objectMapper.writeValueAsString(commentRequest);

        mvc.perform(post("/v1/board/AAA/posts/1/comments")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("modifyComment :: 정상 케이스")
    void modifyComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContent("test1's modified comment");

        String content = objectMapper.writeValueAsString(commentRequest);

        mvc.perform(put("/v1/board/AAA/posts/1/comments/1")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
}