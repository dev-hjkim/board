package com.example.comment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentRequest {
    private String boardName;
    private String postSeq;
    private String commentSeq;
    private String content;
}
