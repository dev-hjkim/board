package com.example.comment.repository;

import com.example.comment.model.Comment;
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
class CommentRepositoryTest {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    @DisplayName("getTotalList :: 정상 케이스")
    void getTotalList() {
        int totalCount = commentRepository.getTotalList("1");

        assertThat(totalCount, is(2));
    }

    @Test
    @DisplayName("getCommentList :: 정상 케이스")
    void getCommentList() {
        Comment comment = new Comment("AAA", "1", 0, 10);

        List<Comment> commentList = commentRepository.getCommentList(comment);
        assertThat(commentList.get(0).getBoardNo(), is(comment.getBoardNo()));
        assertThat(commentList.size(), is(2));
    }
}