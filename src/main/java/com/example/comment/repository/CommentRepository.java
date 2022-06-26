package com.example.comment.repository;

import com.example.comment.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {
    int getTotalCount(String postSeq);

    List<Comment> getCommentList(Comment comment);

    Comment getComment(Comment comment);

    void deleteComment(Comment comment);

    void insertComment(Comment comment);

    void updateComment(Comment comment);
}
