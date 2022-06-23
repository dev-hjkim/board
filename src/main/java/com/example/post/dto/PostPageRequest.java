package com.example.post.dto;

import com.example.common.dto.Page;
import lombok.Data;

@Data
public class PostPageRequest extends Page {
    private String boardName;

    private String postSeq;
}
