package com.example.post.controller.v1;

import com.example.board.service.BoardService;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.exception.InvalidParameterException;
import com.example.post.dto.PostBody;
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

    private final BoardService boardService;
    private final PostService postService;

    /**
     * 포스트 목록 조회
     *
     * @author hjkim
     * @param boardSeq, pageIndex(nullable), pageSize(nullable),
     * @return PostList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PageList<Post> getPostList(@PathVariable long boardSeq,
                                      PageRequest pageRequest) {
        logger.info("getPostList ::: {} {}", boardSeq, pageRequest);

        checkExistence(boardSeq);

        return postService.getPostList(pageRequest, boardSeq);
    }


    /**
     * 포스트 조회
     *
     * @author hjkim
     * @param boardSeq, postSeq
     * @return Post
     */
    @GetMapping(value="/{postSeq}")
    public Post getPost(@RequestAttribute long userSeq,
                        @PathVariable long boardSeq,
                        @PathVariable long postSeq) {
        logger.info("getPost ::: {} {} {}",
                userSeq, boardSeq, postSeq);

        checkExistence(boardSeq);

        Post post = getValidatedPost(userSeq, boardSeq, postSeq);

        return postService.getPostAndIncreaseViewCount(post.getPostNo());
    }


    /**
     * 포스트 삭제
     *
     * @author hjkim
     * @param boardSeq, postSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{postSeq}")
    public Result deletePost(@RequestAttribute long userSeq,
                             @PathVariable long boardSeq,
                             @PathVariable long postSeq) {
        logger.info("deletePost ::: {} {} {}",
                userSeq, boardSeq, postSeq);

        checkExistence(boardSeq);

        Post post = getValidatedPost(userSeq, boardSeq, postSeq);

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
    public Post createPost(@RequestAttribute long userSeq,
                           @PathVariable long boardSeq,
                           @RequestBody PostBody body) {
        logger.info("createPost ::: {} {} {}",
                userSeq, boardSeq, body);

        checkExistence(boardSeq);

        Post post = Post.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
                .build();

        post.setTitle(body.getTitle());
        post.setContent(body.getContent());

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
    public Post modifyPost(@RequestAttribute long userSeq,
                           @PathVariable long boardSeq,
                           @PathVariable long postSeq,
                           @RequestBody PostBody body) {
        logger.info("modifyPost ::: {} {} {} {}",
                userSeq, boardSeq, postSeq, body);

        checkExistence(boardSeq);

        Post post = getValidatedPost(userSeq, boardSeq, postSeq);

        post.setTitle(body.getTitle());
        post.setContent(body.getContent());

        return postService.modifyPost(post);
    }


    private void checkExistence(long boardSeq) {
        boardService.validateBoardSeq(boardSeq);
    }


    private Post getValidatedPost(long userSeq, long boardSeq, long postSeq) {
        postService.validatePostSeq(postSeq);

        Post post = postService.getPost(postSeq);

        validatePost(post, userSeq, boardSeq);
        return post;
    }


    private void validatePost(Post post, long userSeq, long boardSeq) {
        if (post.getBoardNo() != boardSeq) {
            throw new InvalidParameterException();
        }

        if (post.getMemberNo() != userSeq) {
            throw new InvalidParameterException();
        }
    }
}
