package com.example.comment.dto;

import com.example.common.dto.Page;
import lombok.Data;

@Data
public class CommentPageRequest extends Page {
    private String boardName;

    private String postSeq;
}
