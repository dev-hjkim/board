package com.example.comment.service.impl;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.common.dto.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class CommentServiceImplTest {
    private CommentServiceImpl commentService;

    @Autowired
    public void setCommentServiceImplTest(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Test
    @DisplayName("getCommentList :: 정상 케이스")
    void getCommentList() {
        Comment comment = Comment.builder()
                .boardCd("AAA")
                .boardNo("1")
                .startPage(0)
                .pageSize(10)
                .build();

        CommentList result = commentService.getCommentList(comment);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardNo(), is(comment.getBoardNo()));
    }

    @Test
    @DisplayName("getComment :: 정상 케이스")
    void getComment() {
        Comment commentRequest = Comment.builder()
                .boardCd("AAA")
                .boardNo("1")
                .commentNo("1")
                .build();

        Comment result = commentService.getComment(commentRequest);

        assertThat(result.getContent(), is("test1's comment"));
    }

    @Test
    @Transactional
    @DisplayName("deleteComment :: 정상 케이스")
    void deleteComment() {
        Comment commentRequest = Comment.builder()
                .memberNo("7")
                .boardCd("AAA")
                .boardNo("1")
                .commentNo("1")
                .build();

        ResultType resultType = commentService.deleteComment(commentRequest);

        assertThat(resultType.getCode(), is("200000"));
    }

    @Test
    @Transactional
    @DisplayName("createComment :: 정상 케이스")
    void createComment() {
        Comment commentRequest = Comment.builder()
                .memberNo("7")
                .boardCd("AAA")
                .boardNo("1")
                .content("test1's new comment")
                .build();

        Comment result = commentService.createComment(commentRequest);

        assertThat(result.getContent(), is(commentRequest.getContent()));
    }

    @Test
    @Transactional
    @DisplayName("modifyComment :: 정상 케이스")
    void modifyComment() {
        Comment commentRequest = Comment.builder()
                .memberNo("7")
                .boardCd("AAA")
                .boardNo("1")
                .commentNo("1")
                .content("test1's modified comment")
                .build();

        Comment result = commentService.modifyComment(commentRequest);

        assertThat(result.getContent(), is(commentRequest.getContent()));
    }
}