package com.example.post.service.impl;

import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
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
        Post selectedPost = postRepository.getPost(post);

        if (selectedPost == null) {
            throw new DataNotFoundException();
        }

        int updatedViewCount = selectedPost.getViewCnt() + 1;
        selectedPost.setViewCnt(updatedViewCount);

        postRepository.updateViewCount(selectedPost);
        return selectedPost;
    }

    @Override
    @Transactional
    public ResultType deletePost(Post post) {
        checkEditable(post);

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
        checkEditable(post);

        postRepository.updatePost(post);
        return post;
    }

    private void checkEditable(Post post) {
        Post selectedPost = postRepository.getPost(post);

        if (selectedPost == null) {
            throw new DataNotFoundException();
        }

        if (!selectedPost.getMemberNo().equals(post.getMemberNo())) {
            throw new NoAuthorityException();
        }
    }
}
