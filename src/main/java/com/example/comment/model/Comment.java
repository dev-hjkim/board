package com.example.comment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private String boardNo;
    private String boardCd;

    private String commentNo;
    private String memberNo;
    private String userId;
    private String content;
    private Date updDt;

    private Integer startPage;
    private Integer pageSize;

    public Comment(String boardName, String postSeq, Integer startPage, Integer pageSize) {
        this.boardCd = boardName;
        this.boardNo = postSeq;
        this.startPage = startPage;
        this.pageSize = pageSize;
    }
}
