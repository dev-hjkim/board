package com.example.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
