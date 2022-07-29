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
    private String postNo;

    private String boardNo;
    private String name;

    private String memberNo;
    private String userId;

    private String title;
    private String content;
    private int viewCnt;
    private int replyCnt;

    private Date regDt;
    private Date updDt;
}
