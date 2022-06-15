package com.example.board.controller.v1;

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
class BoardControllerTest {

    private MockMvc mvc;
    private JwtUtil jwtUtil;
    String accessToken;

    @Autowired
    public void setAuthControllerTest(MockMvc mvc, JwtUtil jwtUtil) {
        this.mvc = mvc;
        this.jwtUtil = jwtUtil;
        this.accessToken = jwtUtil.generate("5", "ACCESS");
    }

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() throws Exception {
        mvc.perform(get("/v1/board")
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}