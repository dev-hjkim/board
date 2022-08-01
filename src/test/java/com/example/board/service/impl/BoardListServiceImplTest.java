package com.example.board.service.impl;

import com.example.board.model.Board;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class BoardListServiceImplTest {

    private BoardServiceImpl boardService;

    @Autowired
    public void setPostServiceImplTest(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(2);
        pageRequest.setPageSize(10);

        PageList<Board> result = boardService.getBoardList(pageRequest);

        assertThat(result.getTotalCount(), is(12));
        assertThat(result.getTotalPage(), is(2));
        assertThat(result.getList().get(0).getName(), is("KKK"));
    }
}