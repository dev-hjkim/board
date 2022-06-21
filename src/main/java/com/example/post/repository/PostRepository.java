package com.example.post.repository;

import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalList(String boardName);

    List<Post> getPostList(PostRequest request);

    Post getPost(PostRequest request);
}
