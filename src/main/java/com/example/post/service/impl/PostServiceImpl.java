package com.example.post.service.impl;

import com.example.post.dto.Post;
import com.example.post.model.PostPage;
import com.example.post.model.PostItem;
import com.example.post.repository.PostRepository;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post getPostList(PostPage page) {
        int totalCount = postRepository.getTotalList(page.getBoardName());
        List<PostItem> postItemList = postRepository.getPostList(page);
        return new Post(page.getPageSize(), totalCount, postItemList);
    }
}
