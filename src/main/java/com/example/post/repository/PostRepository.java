package com.example.post.repository;

import com.example.common.dto.PageRequest;
import com.example.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalCount(String boardNo);

    List<Post> getPostList(PageRequest pageRequest, Post post);

    Post getPost(Post post);

    void updateViewCount(Post post);

    void deletePost(Post post);

    void insertPost(Post post);

    void updatePost(Post post);
}
