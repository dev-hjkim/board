package com.example.comment.service;

import com.example.comment.model.Comment;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;

public interface CommentService {
    PageList<Comment> getCommentList(PageRequest pageRequest, long postSeq);

    Comment getComment(long commentSeq);

    Result deleteComment(Comment comment);

    Comment createComment(Comment comment);

    Comment modifyComment(Comment comment);

    boolean isCommentExist(long postSeq);
}
