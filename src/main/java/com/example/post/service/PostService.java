package com.example.post.service;

import com.example.common.dto.ResultType;
import com.example.post.dto.PostList;
import com.example.post.model.Post;

public interface PostService {
    PostList getPostList(Post post);

    Post getPost(Post post);

    ResultType deletePost(Post post);

    Post createPost(Post post);
}
