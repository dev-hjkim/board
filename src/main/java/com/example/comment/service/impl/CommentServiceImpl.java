package com.example.comment.service.impl;

import com.example.comment.dto.CommentList;
import com.example.comment.model.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
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
        int totalCount = commentRepository.getTotalCount(comment.getPostNo());
        List<Comment> commentList = commentRepository.getCommentList(comment);
        return new CommentList(comment.getPageSize(), totalCount, commentList);
    }

    @Override
    public Comment getComment(Comment comment) {
        return commentRepository.getComment(comment);
    }

    @Override
    @Transactional
    public Result deleteComment(Comment comment) {
        checkEditable(comment);

        commentRepository.deleteComment(comment);
        return new Result(ResultType.OK);
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        commentRepository.insertComment(comment);
        commentRepository.updateReplyCount(comment.getPostNo());
        return comment;
    }

    @Override
    @Transactional
    public Comment modifyComment(Comment comment) {
        checkEditable(comment);

        commentRepository.updateComment(comment);
        return comment;
    }


    private void checkEditable(Comment comment) {
        Comment selectedComment = commentRepository.getComment(comment);

        if (selectedComment == null) {
            throw new DataNotFoundException();
        }

        if (!selectedComment.getMemberNo().equals(comment.getMemberNo())) {
            throw new NoAuthorityException();
        }
    }
}
