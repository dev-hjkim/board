package com.example.comment.service.impl;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentList getCommentList(Comment comment) {
        int totalCount = commentRepository.getTotalList(comment.getBoardNo());
        List<Comment> commentList = commentRepository.getCommentList(comment);
        return new CommentList(comment.getPageSize(), totalCount, commentList);
    }
}
