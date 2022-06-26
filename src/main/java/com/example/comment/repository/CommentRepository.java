package com.example.comment.repository;

import com.example.comment.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {
    int getTotalList(String postSeq);

    List<Comment> getCommentList(Comment comment);
}
