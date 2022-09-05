package com.example.comment.service.impl;

import com.example.comment.model.Comment;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.exception.DataNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(10);

        PageList<Comment> result = commentService.getCommentList(pageRequest, 1);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getPostNo(), is(1l));
    }

    @Test
    @DisplayName("getComment :: 정상 케이스")
    void getComment() {
        Comment result = commentService.getComment(1);

        assertThat(result.getContent(), is("test1's comment"));
    }

    @Test
    @Transactional
    @DisplayName("deleteComment :: 정상 케이스")
    void deleteComment() {
        Comment commentRequest = commentService.getComment(1);

        Result result = commentService.deleteComment(commentRequest);

        assertThat(result.getCode(), is("200000"));
    }

    @Test
    @Transactional
    @DisplayName("createComment :: 정상 케이스")
    void createComment() {
        Comment commentRequest = Comment.builder()
                .memberNo(7)
                .postNo(1)
                .build();

        commentRequest.setContent("test1's new comment");

        Comment result = commentService.createComment(commentRequest);

        assertThat(result.getContent(), is(commentRequest.getContent()));
    }

    @Test
    @Transactional
    @DisplayName("modifyComment :: 정상 케이스")
    void modifyComment() {
        Comment commentRequest = commentService.getComment(1);

        commentRequest.setContent("test1's modified comment");

        Comment result = commentService.modifyComment(commentRequest);

        assertThat(result.getContent(), is(commentRequest.getContent()));
    }

    @Test
    @DisplayName("validateCommentSeq :: 예외 케이스")
    void validateCommentSeq() {
        DataNotFoundException thrown = assertThrows(DataNotFoundException.class,
                () -> commentService.validateCommentSeq(1, 14, 5));
        assertEquals("Data not found", thrown.getMessage());
    }
}