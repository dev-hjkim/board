package com.example.comment.service.impl;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.post.dto.PostList;
import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Comment comment = new Comment("AAA", "1", 0, 10);

        CommentList result = commentService.getCommentList(comment);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardNo(), is(comment.getBoardNo()));
    }
}