package com.example.post.service.impl;

import com.example.common.dto.ResultType;
import com.example.post.dto.PostList;
import com.example.post.model.PostPageRequest;
import com.example.post.model.PostRequest;
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
    public PostList getPostList(PostPageRequest request) {
        int totalCount = postRepository.getTotalList(request.getBoardName());
        List<Post> postList = postRepository.getPostList(request);
        return new PostList(request.getPageSize(), totalCount, postList);
    }

    @Override
    public Post getPost(PostRequest request) {
        return postRepository.getPost(request);
    }

    @Override
    @Transactional
    public ResultType deletePost(PostRequest request) {
        postRepository.deletePost(request);
        return ResultType.OK;
    }
}
