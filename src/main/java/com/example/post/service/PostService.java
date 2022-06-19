package com.example.post.service;

import com.example.post.dto.Post;
import com.example.post.model.PostPage;

public interface PostService {
    Post getPostList(PostPage page);
}
