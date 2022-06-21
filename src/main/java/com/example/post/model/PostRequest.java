package com.example.post.model;

import com.example.common.dto.Page;
import lombok.Data;

@Data
public class PostRequest extends Page {
    private String boardName;

    private String postSeq;
}
