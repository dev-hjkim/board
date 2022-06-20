package com.example.post.model;

import com.example.common.dto.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PostPage extends Page {
    @Setter
    private String boardName;
}
