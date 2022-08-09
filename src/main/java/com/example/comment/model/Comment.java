package com.example.comment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private long commentNo;

    private long boardNo;
    private long postNo;

    private long memberNo;
    private String userId;

    @Setter
    private String content;

    private Date regDt;
    private Date updDt;

    @Builder
    public Comment(long boardNo, long postNo, long memberNo) {
        this.boardNo = boardNo;
        this.postNo = postNo;
        this.memberNo = memberNo;
    }
}
