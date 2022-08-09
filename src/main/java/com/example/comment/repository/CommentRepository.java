package com.example.comment.repository;

import com.example.comment.model.Comment;
import com.example.common.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentRepository {
    int getTotalCount(long postNo);

    List<Comment> getCommentList(PageRequest pageRequest, Comment comment);

    Comment getComment(long commentSeq);

    void deleteComment(long commentSeq);

    void insertComment(Comment comment);

    void updateReplyCount(long postNo);

    void updateComment(Comment comment);

}
