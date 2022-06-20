package com.example.post.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostItem {
    private int boardNo;
    private String boardCd;
    private String title;
    private int memberNo;
    private String userId;
    private int viewCnt;
    private int replyCnt;
    private Date regDt;
    private Date updDt;
}
