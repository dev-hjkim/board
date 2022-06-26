package com.example.post.controller.v1;

import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
import com.example.post.dto.PostList;
import com.example.post.dto.PostRequest;
import com.example.post.model.Post;
import com.example.post.dto.PostPageRequest;
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

        Post post = new Post(request.getBoardName(), request.getStartPage(),
                request.getPageSize());
        return postService.getPostList(post);
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

        Post post = new Post(request.getBoardName(), request.getPostSeq());
        return postService.getPost(post);
    }

    /**
     * 포스트 삭제
     *
     * @author hjkim
     * @param request-boardName, postSeq
     * @return ResultType
     */
    @DeleteMapping(value="/posts/{postSeq}")
    public ResultType deletePost(@RequestAttribute String userSeq,
                                 PostRequest request) {
        logger.info("deletePost ::: {} {}", userSeq, request);

        Post post = new Post(request.getBoardName(), request.getPostSeq());

        checkEditable(userSeq, post);

        return postService.deletePost(post);
    }

    /**
     * 포스트 수정
     *
     * @author hjkim
     * @param request-boardName, title, content
     * @return Post
     */
    @PostMapping(value="/posts")
    public Post createPost(@RequestAttribute String userSeq,
                           @PathVariable String boardName,
                           @RequestBody PostRequest request) {
        logger.info("createPost ::: {} {} {}", userSeq, boardName, request);

        Post post = new Post(boardName, request.getTitle(),
                request.getContent(), userSeq);
        return postService.createPost(post);
    }

    /**
     * 포스트 수정
     *
     * @author hjkim
     * @param request-boardName, postSeq, title, content
     * @return Post
     */
    @PutMapping(value="/posts/{postSeq}")
    public Post modifyPost(@RequestAttribute String userSeq,
                           PostRequest request,
                           @RequestBody PostRequest body) {
        logger.info("modifyPost ::: {} {} {}", userSeq, request, body);

        Post post = new Post(request.getBoardName(), request.getPostSeq(), body.getTitle(),
                body.getContent(), userSeq);

        checkEditable(userSeq, post);

        return postService.modifyPost(post);
    }



    private void checkEditable(String userSeq, Post post) {
        Post selectedPost = postService.getPost(post);

        if (selectedPost == null) {
            throw new DataNotFoundException();
        }

        if (!selectedPost.getMemberNo().equals(userSeq)) {
            throw new NoAuthorityException();
        }
    }
}
