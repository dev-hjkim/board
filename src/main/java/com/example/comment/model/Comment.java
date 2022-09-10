package com.example.comment.model;

import com.example.common.model.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment extends Timestamp {
    private long commentNo;

    private long postNo;

    private long memberNo;
    private String userId;

    @Setter
    private String content;

    @Builder
    public Comment(long postNo, long memberNo) {
        this.postNo = postNo;
        this.memberNo = memberNo;
    }
}
