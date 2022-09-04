package com.example.board.repository;

import com.example.board.model.Board;
import com.example.common.dto.PageRequest;
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
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(2);
        pageRequest.setPageSize(10);

        List<Board> boardList = boardRepository.getBoardList(pageRequest);
        assertThat(boardList.get(0).getName(), is("KKK"));
    }

    @Test
    @DisplayName("getTotalList :: 정상 케이스")
    void getTotalList() {
        int totalCount = boardRepository.getTotalList();
        assertThat(totalCount, is(12));
    }

    @Test
    @DisplayName("isExist :: 정상 케이스")
    void isExist() {
        boolean isExist = boardRepository.isExist(1);
        assertThat(isExist, is(true));
    }
}