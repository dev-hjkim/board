package com.example.post.service.impl;

import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
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
    public PageList<Post> getPostList(PageRequest pageRequest, Post post) {
        int totalCount = postRepository.getTotalCount(post.getBoardNo());
        List<Post> postList = postRepository.getPostList(pageRequest, post);
        return new PageList<>(pageRequest.getPageSize(), totalCount, postList);
    }

    @Override
    @Transactional
    public Post getPost(long postSeq) {
        postRepository.updateViewCount(postSeq);
        return postRepository.getPost(postSeq);
    }

    @Override
    @Transactional
    public Result deletePost(Post post) {
        postRepository.deletePost(post.getPostNo());
        return new Result(ResultType.OK);
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
