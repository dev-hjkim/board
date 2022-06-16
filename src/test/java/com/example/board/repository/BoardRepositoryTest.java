package com.example.board.repository;

import com.example.common.dto.Page;
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
class BoardRepositoryTest {
    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    @DisplayName("getBoardList :: 정상 케이스")
    void getBoardList() {
        List<String> boardList = boardRepository.getBoardList(new Page());
        assertThat(boardList.get(0), is("AAA"));
        assertThat(boardList.size(), is(1));
    }
}