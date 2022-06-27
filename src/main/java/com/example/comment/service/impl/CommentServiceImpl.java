package com.example.comment.service.impl;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import com.example.common.dto.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentList getCommentList(Comment comment) {
        int totalCount = commentRepository.getTotalCount(comment.getBoardNo());
        List<Comment> commentList = commentRepository.getCommentList(comment);
        return new CommentList(comment.getPageSize(), totalCount, commentList);
    }

    @Override
    public Comment getComment(Comment comment) {
        return commentRepository.getComment(comment);
    }

    @Override
    @Transactional
    public ResultType deleteComment(Comment comment) {
        commentRepository.deleteComment(comment);
        return ResultType.OK;
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        commentRepository.insertComment(comment);
        return comment;
    }

    @Override
    @Transactional
    public Comment modifyComment(Comment comment) {
        commentRepository.updateComment(comment);
        return comment;
    }
}
