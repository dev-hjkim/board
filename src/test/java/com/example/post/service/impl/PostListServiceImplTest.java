package com.example.post.service.impl;

import com.example.common.dto.ResultType;
import com.example.post.dto.PostList;
import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
class PostListServiceImplTest {
    private PostServiceImpl postService;

    @Autowired
    public void setPostServiceImplTest(PostServiceImpl postService) {
        this.postService = postService;
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        Post post = new Post("AAA", 0, 10);

        PostList result = postService.getPostList(post);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardCd(), is("AAA"));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        Post postRequest = new Post("AAA", "13");

        Post result = postService.getPost(postRequest);

        assertThat(result.getTitle(), is("test13"));
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() {
        Post postRequest = new Post("AAA", "13");

        ResultType resultType = postService.deletePost(postRequest);

        assertThat(resultType.getCode(), is("200000"));
    }

    @Test
    @Transactional
    @DisplayName("createPost :: 정상 케이스")
    void createPost() {
        Post postRequest = new Post("AAA", "test14",
                "test14's content", "5");

        Post result = postService.createPost(postRequest);

        assertThat(result.getTitle(), is("test14"));
    }
}