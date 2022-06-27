package com.example.post.dto;

import com.example.common.dto.PageList;
import com.example.post.model.Post;

import java.util.List;

public class PostList extends PageList<Post> {
    public PostList(int pageSize, int totalCount, List<Post> list) {
        super(pageSize, totalCount, list);
    }
}
