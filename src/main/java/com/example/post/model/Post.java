package com.example.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private long postNo;

    private long memberNo;
    private String userId;

    private long boardNo;
    private String name;

    @Setter
    private String title;
    @Setter
    private String content;

    private int viewCnt;
    private int replyCnt;

    private Date regDt;
    private Date updDt;

    @Builder
    public Post(long boardNo, long memberNo) {
        this.boardNo = boardNo;
        this.memberNo = memberNo;
        this.viewCnt = 0;
        this.replyCnt = 0;
    }
}
