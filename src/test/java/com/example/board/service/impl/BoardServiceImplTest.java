package com.example.board.service.impl;

import com.example.board.dto.Board;
import com.example.common.dto.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class BoardServiceImplTest {

    private BoardServiceImpl boardService;

    @Autowired
    public void setPostServiceImplTest(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        Board result = boardService.getBoardList(new Page());

        assertThat(result.getTotalCount(), is(12));
        assertThat(result.getTotalPage(), is(2));
        assertThat(result.getList().get(0), is("AAA"));
    }
}