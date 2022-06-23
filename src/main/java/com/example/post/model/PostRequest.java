package com.example.post.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRequest {
    private String boardName;
    private String postSeq;
    private String userSeq;
}
