package com.example.comment.service;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.common.dto.Result;

public interface CommentService {
    CommentList getCommentList(Comment comment);

    Comment getComment(Comment comment);

    Result deleteComment(Comment comment);

    Comment createComment(Comment comment);

    Comment modifyComment(Comment comment);
}
