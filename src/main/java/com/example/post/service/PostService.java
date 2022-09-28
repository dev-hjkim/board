package com.example.post.service;

import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.post.model.Post;

public interface PostService {
    PageList<Post> getPostList(PageRequest pageRequest, long boardSeq);

    Post getPost(long postSeq);

    Post getPostAndIncreaseViewCount(long postSeq);

    Post updateReplyCount(long postSeq);

    Result deletePost(Post post);

    Post createPost(Post post);

    Post modifyPost(Post post);

    void validatePostSeq(long postSeq);
}
