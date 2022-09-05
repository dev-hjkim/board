package com.example.comment.repository;

import com.example.comment.model.Comment;
import com.example.common.dto.PageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        int totalCount = commentRepository.getTotalCount(1);

        assertThat(totalCount, is(2));
    }

    @Test
    @DisplayName("getCommentList :: 정상 케이스")
    void getCommentList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(10);

        List<Comment> commentList = commentRepository.getCommentList(pageRequest, 1);
        assertThat(commentList.get(0).getPostNo(), is(1l));
        assertThat(commentList.size(), is(2));
    }

    @Test
    @DisplayName("getComment :: 정상 케이스")
    void getComment() {
        Comment comment = commentRepository.getComment(1);
        assertThat(comment.getContent(), is("test1's comment"));
    }

    @Test
    @Transactional
    @DisplayName("deleteComment :: 정상 케이스")
    void deleteComment() {
        commentRepository.deleteComment(1);

        Comment comment = commentRepository.getComment(1);
        assertThat(comment, nullValue());
    }

    @Test
    @Transactional
    @DisplayName("insertComment :: 정상 케이스")
    void insertComment() {
        Comment commentRequest = Comment.builder()
                .memberNo(7)
                .postNo(1)
                .build();

        commentRequest.setContent("test1's new comment");

        commentRepository.insertComment(commentRequest);
        assertThat(commentRequest.getCommentNo(), instanceOf(Long.TYPE));
    }

    @Test
    @Transactional
    @DisplayName("updateComment :: 정상 케이스")
    void updateComment() {
        Comment commentRequest = commentRepository.getComment(1);
        commentRequest.setContent("test1's modified comment");

        commentRepository.updateComment(commentRequest);
        assertThat(commentRequest.getCommentNo(), is(1l));
    }
}