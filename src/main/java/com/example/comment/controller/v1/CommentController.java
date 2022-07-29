package com.example.comment.controller.v1;

import com.example.comment.dto.CommentRequest;
import com.example.comment.model.Comment;
import com.example.comment.service.CommentService;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/boards/{boardSeq}/posts/{postSeq}/comments")
@RequiredArgsConstructor
public class CommentController {
    final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    /**
     * 댓글 목록 조회
     *
     * @author hjkim
     * @param boardSeq, postSeq, pageIndex(nullable), pageSize(nullable)
     * @return CommentList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PageList<Comment> getCommentList(@PathVariable String boardSeq,
                                            @PathVariable String postSeq,
                                            PageRequest pageRequest) {
        logger.info("getCommentList ::: {} {} {}", boardSeq, postSeq, pageRequest);

        Comment comment = Comment.builder()
                .boardNo(boardSeq)
                .postNo(postSeq)
                .build();
        return commentService.getCommentList(pageRequest, comment);
    }

    /**
     * 댓글 삭제
     *
     * @author hjkim
     * @param boardSeq, postSeq, commentSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{commentSeq}")
    public Result deleteComment(@RequestAttribute String userSeq,
                                @PathVariable String boardSeq,
                                @PathVariable String postSeq,
                                @PathVariable String commentSeq) {
        logger.info("deleteComment ::: {} {} {} {}", userSeq, boardSeq, postSeq, commentSeq);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
                .postNo(postSeq)
                .commentNo(commentSeq)
                .build();
        return commentService.deleteComment(comment);
    }

    /**
     * 댓글 등록
     *
     * @author hjkim
     * @param boardSeq, postSeq, content
     * @return Post
     */
    @PostMapping(value="")
    public Comment createComment(@RequestAttribute String userSeq,
                                 @PathVariable String boardSeq,
                                 @PathVariable String postSeq,
                                 @RequestBody CommentRequest body) {
        logger.info("createComment ::: {} {} {} {}", userSeq, boardSeq, postSeq, body);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
                .postNo(postSeq)
                .content(body.getContent())
                .build();
        return commentService.createComment(comment);
    }

    /**
     * 댓글 수정
     *
     * @author hjkim
     * @param boardSeq, postSeq, commentSeq, content
     * @return Post
     */
    @PutMapping(value="/{commentSeq}")
    public Comment modifyComment(@RequestAttribute String userSeq,
                                 @PathVariable String boardSeq,
                                 @PathVariable String postSeq,
                                 @PathVariable String commentSeq,
                                 @RequestBody CommentRequest body) {
        logger.info("modifyComment ::: {} {} {} {} {}", userSeq, boardSeq, postSeq, commentSeq, body);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardNo(boardSeq)
                .postNo(postSeq)
                .commentNo(commentSeq)
                .content(body.getContent())
                .build();
        return commentService.modifyComment(comment);
    }
}
