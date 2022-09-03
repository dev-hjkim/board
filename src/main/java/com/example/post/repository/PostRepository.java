package com.example.post.repository;

import com.example.common.dto.PageRequest;
import com.example.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalCount(long boardNo);

    List<Post> getPostList(PageRequest pageRequest, long boardNo);

    Post getPost(long postNo);

    void deletePost(long postNo);

    void insertPost(Post post);

    void updatePost(Post post);

    boolean isExist(long boardNo, long postNo);
}
