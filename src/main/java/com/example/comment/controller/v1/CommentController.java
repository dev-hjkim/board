package com.example.comment.controller.v1;

import com.example.board.service.BoardService;
import com.example.comment.dto.CommentBody;
import com.example.comment.model.Comment;
import com.example.comment.service.CommentService;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.common.exception.DataNotFoundException;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/boards/{boardSeq}/posts/{postSeq}/comments")
@RequiredArgsConstructor
public class CommentController {
    final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    /**
     * 댓글 목록 조회
     *
     * @author hjkim
     * @param boardSeq, postSeq, pageIndex(nullable), pageSize(nullable)
     * @return CommentList-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PageList<Comment> getCommentList(@PathVariable long boardSeq,
                                            @PathVariable long postSeq,
                                            PageRequest pageRequest) {
        logger.info("getCommentList ::: {} {} {}",
                boardSeq, postSeq, pageRequest);

        boardService.validateBoardSeq(boardSeq);
        postService.validatePostSeq(postSeq);

        return commentService.getCommentList(pageRequest, postSeq);
    }


    /**
     * 댓글 삭제
     *
     * @author hjkim
     * @param boardSeq, postSeq, commentSeq
     * @return ResultType
     */
    @DeleteMapping(value="/{commentSeq}")
    public Result deleteComment(@RequestAttribute long userSeq,
                                @PathVariable long boardSeq,
                                @PathVariable long postSeq,
                                @PathVariable long commentSeq) {
        logger.info("deleteComment ::: {} {} {} {}",
                userSeq, boardSeq, postSeq, commentSeq);

        Comment comment = getValidatedComment(userSeq, postSeq, commentSeq);

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
    public Comment createComment(@RequestAttribute long userSeq,
                                 @PathVariable long boardSeq,
                                 @PathVariable long postSeq,
                                 @RequestBody CommentBody body) {
        logger.info("createComment ::: {} {} {} {}",
                userSeq, boardSeq, postSeq, body);

        boardService.validateBoardSeq(boardSeq);
        postService.validatePostSeq(postSeq);

        Comment comment = Comment.builder()
                .memberNo(userSeq)
                .postNo(postSeq)
                .build();

        comment.setContent(body.getContent());

        postService.updateReplyCount(postSeq);
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
    public Comment modifyComment(@RequestAttribute long userSeq,
                                 @PathVariable long boardSeq,
                                 @PathVariable long postSeq,
                                 @PathVariable long commentSeq,
                                 @RequestBody CommentBody body) {
        logger.info("modifyComment ::: {} {} {} {} {}",
                userSeq, boardSeq, postSeq, commentSeq, body);

        Comment comment = getValidatedComment(userSeq, postSeq, commentSeq);

        comment.setContent(body.getContent());

        return commentService.modifyComment(comment);
    }


    private Comment getValidatedComment(long userSeq, long postSeq, long commentSeq) {
        Comment comment = commentService.getComment(commentSeq);

        validateComment(comment, userSeq, postSeq);
        return comment;
    }


    private void validateComment(Comment comment, long userSeq, long postSeq) {
        if (comment.getMemberNo() != userSeq) {
            throw new DataNotFoundException();
        }

        if (comment.getPostNo() != postSeq) {
            throw new DataNotFoundException();
        }
    }
}
