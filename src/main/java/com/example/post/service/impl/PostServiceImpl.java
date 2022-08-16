package com.example.post.service.impl;

import com.example.board.repository.BoardRepository;
import com.example.comment.repository.CommentRepository;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NotAllowedOperationException;
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

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public PageList<Post> getPostList(PageRequest pageRequest, Post post) {
        int totalCount = postRepository.getTotalCount(post.getBoardNo());
        List<Post> postList = postRepository.getPostList(pageRequest, post);
        return new PageList<>(pageRequest.getPageSize(), totalCount, postList);
    }

    @Override
    public Post getPost(long postSeq) {
        Post post = postRepository.getPost(postSeq);

        if (post == null) {
            throw new DataNotFoundException();
        }

        return post;
    }

    @Override
    @Transactional
    public Post getPostAndIncreaseViewCount(long postSeq) {
        postRepository.updateViewCount(postSeq);
        return getPost(postSeq);
    }

    @Override
    @Transactional
    public Result deletePost(Post post) {
        if (commentRepository.getTotalCount(post.getPostNo()) > 0) {
            throw new NotAllowedOperationException();
        }

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

    @Override
    public void validateBoardSeq(long boardSeq) {
        if (!boardRepository.isExist(boardSeq)) {
            throw new DataNotFoundException();
        }
    }
}
