package com.example.post.service;

import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.post.model.Post;

public interface PostService {
    PageList<Post> getPostList(PageRequest pageRequest, Post post);

    Post getPost(Post post);

    Result deletePost(Post post);

    Post createPost(Post post);

    Post modifyPost(Post post);
}
