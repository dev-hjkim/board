package com.example.post.controller.v1;

import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
import com.example.post.dto.PostList;
import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import com.example.post.model.PostPageRequest;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 포스트 삭제
     *
     * @author hjkim
     * @param request-boardName, postSeq
     * @return Post
     */
    @DeleteMapping(value="/posts/{postSeq}")
    public ResultType deletePost(@RequestAttribute String userSeq,
                                 PostRequest request) {
        logger.info("deletePost ::: {}", request);

        Post post = postService.getPost(request);

        if (post == null) {
            throw new DataNotFoundException();
        }

        if (!post.getMemberNo().equals(userSeq)) {
            throw new NoAuthorityException();
        }

        return postService.deletePost(request);
    }
}
