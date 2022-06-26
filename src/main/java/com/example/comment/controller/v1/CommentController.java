package com.example.comment.controller.v1;

import com.example.comment.dto.CommentList;
import com.example.comment.dto.CommentPageRequest;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.Comment;
import com.example.comment.service.CommentService;
import com.example.common.dto.ResultType;
import com.example.common.exception.DataNotFoundException;
import com.example.common.exception.NoAuthorityException;
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
     * @param request-pageIndex(nullable), pageSize(nullable), boardName, postSeq
     * @return PostList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public CommentList getCommentList(CommentPageRequest request) {
        logger.info("getCommentList ::: {}", request);

        Comment comment = new Comment(request.getBoardName(), request.getPostSeq(),
                request.getStartPage(), request.getPageSize());
        return commentService.getCommentList(comment);
    }

    /**
     * 댓글 삭제
     *
     * @author hjkim
     * @param request-boardName, postSeq, commentSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{commentSeq}")
    public ResultType deleteComment(@RequestAttribute String userSeq,
                                 CommentRequest request) {
        logger.info("deleteComment ::: {} {}", userSeq, request);

        Comment comment = new Comment(request.getBoardName(), request.getPostSeq(), request.getCommentSeq());

        checkEditable(userSeq, comment);

        return commentService.deleteComment(comment);
    }

    /**
     * 댓글 등록
     *
     * @author hjkim
     * @param request-boardName, postSeq, content
     * @return Post
     */
    @PostMapping(value="")
    public Comment createComment(@RequestAttribute String userSeq,
                                 CommentRequest request,
                                 @RequestBody CommentRequest body) {
        logger.info("createComment ::: {} {} {}", userSeq, request, body);

        Comment comment = new Comment(request.getBoardName(), request.getPostSeq(),
                body.getContent(), userSeq);
        return commentService.createComment(comment);
    }

    /**
     * 댓글 수정
     *
     * @author hjkim
     * @param request-boardName, postSeq, commentSeq, content
     * @return Post
     */
    @PutMapping(value="/{commentSeq}")
    public Comment modifyComment(@RequestAttribute String userSeq,
                           CommentRequest request,
                           @RequestBody CommentRequest body) {
        logger.info("modifyComment ::: {} {} {}", userSeq, request, body);

        Comment comment = new Comment(request.getBoardName(), request.getPostSeq(),
                request.getCommentSeq(), body.getContent(), userSeq);

        checkEditable(userSeq, comment);

        return commentService.modifyComment(comment);
    }

    private void checkEditable(String userSeq, Comment comment) {
        Comment selectedComment = commentService.getComment(comment);

        if (selectedComment == null) {
            throw new DataNotFoundException();
        }

        if (!selectedComment.getMemberNo().equals(userSeq)) {
            throw new NoAuthorityException();
        }
    }
}
