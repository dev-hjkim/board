package com.example.comment.controller.v1;

import com.example.comment.dto.CommentList;
import com.example.comment.dto.CommentPageRequest;
import com.example.comment.model.Comment;
import com.example.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/board/{boardName}/posts/{postSeq}/")
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
    @GetMapping(value="/comments")
    public CommentList getCommentList(CommentPageRequest request) {
        logger.info("getCommentList ::: {}", request);

        Comment comment = new Comment(request.getBoardName(), request.getPostSeq(),
                request.getStartPage(), request.getPageSize());
        return commentService.getCommentList(comment);
    }
}
