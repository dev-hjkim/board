package com.example.comment.repository;

import com.example.comment.model.Comment;
import com.example.common.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {
    int getTotalCount(String postNo);

    List<Comment> getCommentList(PageRequest pageRequest, Comment comment);

    Comment getComment(Comment comment);

    void deleteComment(Comment comment);

    void insertComment(Comment comment);

    void updateReplyCount(String postNo);

    void updateComment(Comment comment);

}
