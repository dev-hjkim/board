package com.example.post.repository;

import com.example.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalList(String boardName);

    List<Post> getPostList(Post request);

    Post getPost(Post post);

    void deletePost(Post post);

    void insertPost(Post post);

    void updatePost(Post post);
}
