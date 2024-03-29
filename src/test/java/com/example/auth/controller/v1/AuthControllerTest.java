package com.example.auth.controller.v1;

import com.example.auth.dto.User;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    private MockMvc mvc;
    private ObjectMapper objectMapper;
    String refreshToken;

    @Autowired
    public void setAuthControllerTest(MockMvc mvc, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
        this.refreshToken = jwtUtil.generate(7, "REFRESH");
    }

    @Test
    @Transactional
    @DisplayName("signin :: 정상 케이스")
    void signin() throws Exception {

        String content = objectMapper.writeValueAsString(
                new User("test1234567", "0000")
        );

        mvc.perform(post("/v1/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("login :: 정상 케이스")
    void login() throws Exception {
        String content = objectMapper.writeValueAsString(
                new User("hjkim", "asdf")
        );

        mvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("refreshToken :: 정상 케이스")
    void refreshToken() throws Exception {
        mvc.perform(get("/v1/auth/refresh")
                        .header("R-Authorization", refreshToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}