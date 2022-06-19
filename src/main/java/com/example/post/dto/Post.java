package com.example.post.dto;

import com.example.common.dto.Pagenation;
import com.example.post.model.PostItem;

import java.util.List;

public class Post extends Pagenation<PostItem> {
    public Post(int pageSize, int totalCount, List<PostItem> list) {
        super(pageSize, totalCount, list);
    }
}
