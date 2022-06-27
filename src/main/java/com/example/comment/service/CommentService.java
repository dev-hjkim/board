package com.example.comment.service;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.common.dto.ResultType;

public interface CommentService {
    CommentList getCommentList(Comment comment);

    Comment getComment(Comment comment);

    ResultType deleteComment(Comment comment);

    Comment createComment(Comment comment);

    Comment modifyComment(Comment comment);
}
