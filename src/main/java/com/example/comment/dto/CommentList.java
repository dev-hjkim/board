package com.example.comment.dto;

import com.example.comment.model.Comment;
import com.example.common.dto.PageList;

import java.util.List;

public class CommentList extends PageList<Comment> {
    public CommentList(int pageSize, int totalCount, List<Comment> list) {
        super(pageSize, totalCount, list);
    }
}
