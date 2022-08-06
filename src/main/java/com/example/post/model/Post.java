package com.example.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String postNo;

    private String boardNo;
    private String name;

    private String memberNo;
    private String userId;

    @Setter
    private String title;
    @Setter
    private String content;

    private int viewCnt;
    private int replyCnt;

    private Date regDt;
    private Date updDt;

    @Builder
    public Post(String boardNo, String memberNo) {
        this.boardNo = boardNo;
        this.memberNo = memberNo;
    }
}
