package com.example.post.controller.v1;

import com.example.post.dto.Post;
import com.example.post.model.PostPage;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param page-pageIndex(nullable), pageSize(nullable)
     * @return Post-totalCount, totalPage, list
     */
    @GetMapping(value="/posts")
    public Post getPostList(@PathVariable String boardName, PostPage page) {
        logger.info("getPostList ::: {} {}", boardName, page);
        page.setBoardName(boardName);

        return postService.getPostList(page);
    }
}
