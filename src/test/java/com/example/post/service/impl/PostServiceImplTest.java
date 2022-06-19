package com.example.post.service.impl;

import com.example.post.dto.Post;
import com.example.post.model.PostPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class PostServiceImplTest {
    private PostServiceImpl postService;

    @Autowired
    public void setPostServiceImplTest(PostServiceImpl postService) {
        this.postService = postService;
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        PostPage page = new PostPage();
        page.setBoardName("AAA");

        Post result = postService.getPostList(page);

        assertThat(result.getTotalCount(), is(1));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardCd(), is("AAA"));
    }
}