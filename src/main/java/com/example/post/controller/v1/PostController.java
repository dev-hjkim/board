package com.example.post.controller.v1;

import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.post.dto.PostRequest;
import com.example.post.model.Post;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/boards/{boardSeq}/posts")
@RequiredArgsConstructor
public class PostController {
    final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    /**
     * 포스트 목록 조회
     *
     * @author hjkim
     * @param boardSeq, pageIndex(nullable), pageSize(nullable),
     * @return PostList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PageList<Post> getPostList(@PathVariable String boardSeq,
                                      PageRequest pageRequest) {
        logger.info("getPostList ::: {} {}", boardSeq, pageRequest);

        Post post = Post.builder()
                .boardNo(boardSeq)
                .build();
        return postService.getPostList(pageRequest, post);
    }

    /**
     * 포스트 조회
     *
     * @author hjkim
     * @param boardSeq, postSeq
     * @return Post
     */
    @GetMapping(value="/{postSeq}")
    public Post getPost(@PathVariable String boardSeq,
                        @PathVariable String postSeq) {
        logger.info("getPost ::: {} {}", boardSeq, postSeq);

        Post post = Post.builder()
                .boardNo(boardSeq)
//                .postNo(postSeq)
                .build();
        return postService.getPost(post);
    }

    /**
     * 포스트 삭제
     *
     * @author hjkim
     * @param boardSeq, postSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{postSeq}")
    public Result deletePost(@RequestAttribute String userSeq,
                             @PathVariable String boardSeq,
                             @PathVariable String postSeq) {
        logger.info("deletePost ::: {} {} {}", userSeq, boardSeq, postSeq);

        Post post = Post.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
//                .postNo(postSeq)
                .build();
        return postService.deletePost(post);
    }

    /**
     * 포스트 등록
     *
     * @author hjkim
     * @param boardSeq, title, content
     * @return Post
     */
    @PostMapping(value="")
    public Post createPost(@RequestAttribute String userSeq,
                           @PathVariable String boardSeq,
                           @RequestBody PostRequest body) {
        logger.info("createPost ::: {} {} {}", userSeq, boardSeq, body);

        Post post = Post.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
//                .title(body.getTitle())
//                .content(body.getContent())
                .build();
        return postService.createPost(post);
    }

    /**
     * 포스트 수정
     *
     * @author hjkim
     * @param boardSeq, postSeq, title, content
     * @return Post
     */
    @PutMapping(value="/{postSeq}")
    public Post modifyPost(@RequestAttribute String userSeq,
                           @PathVariable String boardSeq,
                           @PathVariable String postSeq,
                           @RequestBody PostRequest body) {
        logger.info("modifyPost ::: {} {} {} {}", userSeq, boardSeq, postSeq, body);

        Post post = Post.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
//                .postNo(postSeq)
//                .title(body.getTitle())
//                .content(body.getContent())
                .build();
        return postService.modifyPost(post);
    }
}
