package com.example.comment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private String commentNo;

    private String boardNo;
    private String postNo;

    private String memberNo;
    private String userId;

    @Setter
    private String content;

    private Date regDt;
    private Date updDt;

    @Builder
    public Comment(String boardNo, String postNo, String memberNo) {
        this.boardNo = boardNo;
        this.postNo = postNo;
        this.memberNo = memberNo;
    }
}
