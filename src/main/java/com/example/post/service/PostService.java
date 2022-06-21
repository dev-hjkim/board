package com.example.post.service;

import com.example.post.dto.PostList;
import com.example.post.model.Post;
import com.example.post.model.PostRequest;

public interface PostService {
    PostList getPostList(PostRequest request);

    Post getPost(PostRequest request);
}
