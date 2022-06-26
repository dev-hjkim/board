package com.example.post.service.impl;

import com.example.common.dto.ResultType;
import com.example.post.dto.PostList;
import com.example.post.model.Post;
import com.example.post.repository.PostRepository;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostList getPostList(Post post) {
        int totalCount = postRepository.getTotalCount(post.getBoardCd());
        List<Post> postList = postRepository.getPostList(post);
        return new PostList(post.getPageSize(), totalCount, postList);
    }

    @Override
    public Post getPost(Post post) {
        return postRepository.getPost(post);
    }

    @Override
    @Transactional
    public ResultType deletePost(Post post) {
        postRepository.deletePost(post);
        return ResultType.OK;
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        postRepository.insertPost(post);
        return post;
    }

    @Override
    @Transactional
    public Post modifyPost(Post post) {
        postRepository.updatePost(post);
        return post;
    }
}
