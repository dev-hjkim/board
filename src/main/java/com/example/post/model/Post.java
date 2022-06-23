package com.example.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String boardNo;
    private String boardCd;
    private String title;
    private String content;
    private String memberNo;
    private String userId;
    private int viewCnt;
    private int replyCnt;
    private Date regDt;
    private Date updDt;

    private Integer startPage;
    private Integer pageSize;

    public Post(String boardName, Integer startPage, Integer pageSize) {
        this.boardCd = boardName;
        this.startPage = startPage;
        this.pageSize = pageSize;
    }

    public Post(String boardName, String postSeq) {
        this.boardCd = boardName;
        this.boardNo = postSeq;
    }

    public Post(String boardCd, String title, String content, String memberNo) {
        this.boardCd = boardCd;
        this.title = title;
        this.content = content;
        this.memberNo = memberNo;
    }
}
