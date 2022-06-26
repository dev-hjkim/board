package com.example.comment.service;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;

public interface CommentService {
    CommentList getCommentList(Comment comment);
}
