package com.example.comment.controller.v1;

import com.example.comment.dto.CommentList;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.Comment;
import com.example.comment.service.CommentService;
import com.example.common.dto.PageRequest;
import com.example.common.dto.ResultType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/board/{boardName}/posts/{postSeq}/comments")
@RequiredArgsConstructor
public class CommentController {
    final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    /**
     * 댓글 목록 조회
     *
     * @author hjkim
     * @param boardName, postSeq, pageIndex(nullable), pageSize(nullable)
     * @return CommentList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public CommentList getCommentList(@PathVariable String boardName,
                                      @PathVariable String postSeq,
                                      PageRequest request) {
        logger.info("getCommentList ::: {} {} {}", boardName, postSeq, request);

        Comment comment = Comment.builder()
                .boardCd(boardName)
                .boardNo(postSeq)
                .startPage(request.getStartPage())
                .pageSize(request.getPageSize())
                .build();
        return commentService.getCommentList(comment);
    }

    /**
     * 댓글 삭제
     *
     * @author hjkim
     * @param boardName, postSeq, commentSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{commentSeq}")
    public ResultType deleteComment(@RequestAttribute String userSeq,
                                    @PathVariable String boardName,
                                    @PathVariable String postSeq,
                                    @PathVariable String commentSeq) {
        logger.info("deleteComment ::: {} {} {} {}", userSeq, boardName, postSeq, commentSeq);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardCd(boardName)
                .boardNo(postSeq)
                .commentNo(commentSeq)
                .build();
        return commentService.deleteComment(comment);
    }

    /**
     * 댓글 등록
     *
     * @author hjkim
     * @param boardName, postSeq, content
     * @return Post
     */
    @PostMapping(value="")
    public Comment createComment(@RequestAttribute String userSeq,
                                 @PathVariable String boardName,
                                 @PathVariable String postSeq,
                                 @RequestBody CommentRequest body) {
        logger.info("createComment ::: {} {} {} {}", userSeq, boardName, postSeq, body);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardCd(boardName)
                .boardNo(postSeq)
                .content(body.getContent())
                .build();
        return commentService.createComment(comment);
    }

    /**
     * 댓글 수정
     *
     * @author hjkim
     * @param boardName, postSeq, commentSeq, content
     * @return Post
     */
    @PutMapping(value="/{commentSeq}")
    public Comment modifyComment(@RequestAttribute String userSeq,
                                 @PathVariable String boardName,
                                 @PathVariable String postSeq,
                                 @PathVariable String commentSeq,
                                 @RequestBody CommentRequest body) {
        logger.info("modifyComment ::: {} {} {} {} {}", userSeq, boardName, postSeq, commentSeq, body);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .boardCd(boardName)
                .boardNo(postSeq)
                .commentNo(commentSeq)
                .content(body.getContent())
                .build();
        return commentService.modifyComment(comment);
    }
}
