package com.example.comment.repository;

import com.example.comment.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    @DisplayName("getTotalCount :: 정상 케이스")
    void getTotalCount() {
        int totalCount = commentRepository.getTotalCount("1");

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

    @Test
    @DisplayName("getComment :: 정상 케이스")
    void getComment() {
        Comment commentRequest = new Comment("AAA", "1", "1");

        Comment comment = commentRepository.getComment(commentRequest);
        assertThat(comment.getContent(), is("test1's comment"));
    }

    @Test
    @Transactional
    @DisplayName("deleteComment :: 정상 케이스")
    void deleteComment() {
        Comment commentRequest = new Comment("AAA", "1", "1");
        commentRepository.deleteComment(commentRequest);

        Comment comment = commentRepository.getComment(commentRequest);
        assertThat(comment, nullValue());
    }

    @Test
    @Transactional
    @DisplayName("insertComment :: 정상 케이스")
    void insertComment() {
        Comment commentRequest = new Comment("AAA", "1",
                "test1's new comment", "7");

        commentRepository.insertComment(commentRequest);
        assertThat(commentRequest.getContent(), is("test1's new comment"));
    }

    @Test
    @Transactional
    @DisplayName("updateComment :: 정상 케이스")
    void updateComment() {
        Comment commentRequest = new Comment("AAA", "1", "1",
                "test1's modified comment", "7");

        commentRepository.updateComment(commentRequest);
        assertThat(commentRequest.getContent(), is("test1's modified comment"));
    }
}