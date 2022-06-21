package com.example.post.controller.v1;

import com.example.common.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostListControllerTest {

    private MockMvc mvc;
    String accessToken;

    @Autowired
    public void setPostControllerTest(MockMvc mvc, JwtUtil jwtUtil) {
        this.mvc = mvc;
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
}