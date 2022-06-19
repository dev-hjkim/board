package com.example.post.repository;

import com.example.post.model.PostPage;
import com.example.post.model.PostItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalList(String boardName);

    List<PostItem> getPostList(PostPage page);
}
