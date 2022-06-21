package com.example.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private int boardNo;
    private String boardCd;
    private String title;
    private String content;
    private int memberNo;
    private String userId;
    private int viewCnt;
    private int replyCnt;
    private Date regDt;
    private Date updDt;
}
