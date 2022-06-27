package com.example.comment.dto;

import com.example.common.dto.PageRequest;
import lombok.Data;

@Data
public class CommentPageRequestRequest extends PageRequest {
    private String boardName;

    private String postSeq;
}
