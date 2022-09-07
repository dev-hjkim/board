package com.example.comment.service.impl;

import com.example.comment.model.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public PageList<Comment> getCommentList(PageRequest pageRequest, long postSeq) {
        int totalCount = commentRepository.getTotalCount(postSeq);
        List<Comment> commentList = commentRepository.getCommentList(pageRequest, postSeq);
        return new PageList<>(pageRequest.getPageSize(), totalCount, commentList);
    }

    public Comment getComment(long commentSeq) {
        return commentRepository.getComment(commentSeq);
    }

    @Override
    @Transactional
    public Result deleteComment(Comment comment) {
        commentRepository.deleteComment(comment.getCommentNo());
        return new Result(ResultType.OK);
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

    @Override
    public void validateCommentSeq(long commentSeq) {
        if (!commentRepository.isExist(commentSeq)) {
            throw new DataNotFoundException();
        }
    }
}
