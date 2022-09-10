package com.example.post.model;

import com.example.common.model.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post extends Timestamp {
    private long postNo;

    private long memberNo;
    private String userId;

    private long boardNo;
    private String name;

    @Setter
    private String title;
    @Setter
    private String content;

    @Setter
    private int viewCnt;
    @Setter
    private int replyCnt;

    @Builder
    public Post(long boardNo, long memberNo) {
        this.boardNo = boardNo;
        this.memberNo = memberNo;
        this.viewCnt = 0;
        this.replyCnt = 0;
    }
}
