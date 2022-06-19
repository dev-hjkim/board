package com.example.post.model;

import lombok.Data;

import java.util.Date;

@Data
public class PostItem {
    int boardNo;
    String boardCd;
    String title;
    int memberNo;
    String userId;
    int viewCnt;
    int replyCnt;
    Date regDt;
    Date updDt;
}
