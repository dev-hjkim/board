package com.example.board.service.impl;

import com.example.board.dto.Board;
import com.example.board.repository.BoardRepository;
import com.example.common.dto.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        Board result = boardService.getBoardList(new Page());

        assertThat(result.getTotalCount(), is(1));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0), is("AAA"));
    }
}