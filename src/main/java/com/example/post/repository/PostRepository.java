package com.example.post.repository;

import com.example.post.model.PostPageRequest;
import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    int getTotalList(String boardName);

    List<Post> getPostList(PostPageRequest request);

    Post getPost(PostRequest request);

    void deletePost(PostRequest request);
}
