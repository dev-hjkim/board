package com.example.board.repository;

import com.example.board.model.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardListRepositoryTest {
    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    @DisplayName("getTotalList :: 정상 케이스")
    void getTotalList() {
        int totalCount = boardRepository.getTotalList();
        assertThat(totalCount, is(12));
    }

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        Board board = Board.builder()
                .startPage(0)
                .pageSize(10)
                .build();

        List<String> boardList = boardRepository.getBoardList(board);
        assertThat(boardList.get(0), is("AAA"));
    }
}