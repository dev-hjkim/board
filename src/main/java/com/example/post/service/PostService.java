package com.example.post.service;

import com.example.common.dto.ResultType;
import com.example.post.dto.PostList;
import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import com.example.post.model.PostPageRequest;

public interface PostService {
    PostList getPostList(PostPageRequest request);

    Post getPost(PostRequest request);

    ResultType deletePost(PostRequest request);
}
