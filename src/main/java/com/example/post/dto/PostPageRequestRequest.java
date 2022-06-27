package com.example.post.dto;

import com.example.common.dto.PageRequest;
import lombok.Data;

@Data
public class PostPageRequestRequest extends PageRequest {
    private String boardName;

    private String postSeq;
}
