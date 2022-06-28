package com.example.post.controller.v1;

import com.example.common.dto.PageRequest;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
import com.example.post.dto.PostList;
import com.example.post.dto.PostRequest;
import com.example.post.model.Post;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/board/{boardName}/posts")
@RequiredArgsConstructor
public class PostController {
    final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    /**
     * 포스트 목록 조회
     *
     * @author hjkim
     * @param boardName, pageIndex(nullable), pageSize(nullable),
     * @return PostList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PostList getPostList(@PathVariable String boardName,
                                PageRequest request) {
        logger.info("getPostList ::: {} {}", boardName, request);

        Post post = new Post(boardName,
                request.getStartPage(),request.getPageSize());
        return postService.getPostList(post);
    }

    /**
     * 포스트 조회
     *
     * @author hjkim
     * @param boardName, postSeq
     * @return Post
     */
    @GetMapping(value="/{postSeq}")
    public Post getPost(@PathVariable String boardName,
                        @PathVariable String postSeq) {
        logger.info("getPost ::: {} {}", boardName, postSeq);

        Post post = new Post(boardName, postSeq);
        return postService.getPost(post);
    }

    /**
     * 포스트 삭제
     *
     * @author hjkim
     * @param boardName, postSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{postSeq}")
    public ResultType deletePost(@RequestAttribute String userSeq,
                                 @PathVariable String boardName,
                                 @PathVariable String postSeq) {
        logger.info("deletePost ::: {} {} {}", userSeq, boardName, postSeq);

        Post post = new Post(boardName, postSeq);

        checkEditable(userSeq, post);

        return postService.deletePost(post);
    }

    /**
     * 포스트 등록
     *
     * @author hjkim
     * @param boardName, title, content
     * @return Post
     */
    @PostMapping(value="")
    public Post createPost(@RequestAttribute String userSeq,
                           @PathVariable String boardName,
                           @RequestBody PostRequest body) {
        logger.info("createPost ::: {} {} {}", userSeq, boardName, body);

        Post post = new Post(boardName, body.getTitle(),
                body.getContent(), userSeq);
        return postService.createPost(post);
    }

    /**
     * 포스트 수정
     *
     * @author hjkim
     * @param boardName, postSeq, title, content
     * @return Post
     */
    @PutMapping(value="/{postSeq}")
    public Post modifyPost(@RequestAttribute String userSeq,
                           @PathVariable String boardName,
                           @PathVariable String postSeq,
                           @RequestBody PostRequest body) {
        logger.info("modifyPost ::: {} {} {} {}", userSeq, boardName, postSeq, body);

        Post post = new Post(boardName, postSeq, body.getTitle(),
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
