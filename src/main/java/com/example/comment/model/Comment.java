package com.example.comment.model;

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
}
