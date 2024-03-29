package com.example.comment.repository;

import com.example.comment.model.Comment;
import com.example.common.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {
    int getTotalCount(long postNo);

    List<Comment> getCommentList(PageRequest pageRequest, long postNo);

    Comment getComment(long commentNo);

    void deleteComment(long commentNo);

    void insertComment(Comment comment);

    void updateComment(Comment comment);
}
