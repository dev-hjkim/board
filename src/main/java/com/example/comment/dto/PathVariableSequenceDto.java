package com.example.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PathVariableSequenceDto {
    private long userSeq;
    private long boardSeq;
    private long postSeq;
    private long commentSeq;

    @Builder
    public PathVariableSequenceDto(long userSeq, long boardSeq, long postSeq, long commentSeq) {
        this.userSeq = userSeq;
        this.boardSeq = boardSeq;
        this.postSeq = postSeq;
        this.commentSeq = commentSeq;
    }
}
