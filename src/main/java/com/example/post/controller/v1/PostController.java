package com.example.post.controller.v1;

import com.example.post.dto.PostList;
import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import com.example.post.model.PostPageRequest;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/board/{boardName}")
@RequiredArgsConstructor
public class PostController {
    final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    /**
     * 포스트 목록 조회
     *
     * @author hjkim
     * @param request-pageIndex(nullable), pageSize(nullable), boardName
     * @return PostList-totalCount, totalPage, list
     */
    @GetMapping(value="/posts")
    public PostList getPostList(PostPageRequest request) {
        logger.info("getPostList ::: {}", request);

        return postService.getPostList(request);
    }

    /**
     * 포스트 조회
     *
     * @author hjkim
     * @param request-boardName, postSeq
     * @return Post
     */
    @GetMapping(value="/posts/{postSeq}")
    public Post getPost(PostRequest request) {
        logger.info("getPost ::: {}", request);

        return postService.getPost(request);
    }
}
