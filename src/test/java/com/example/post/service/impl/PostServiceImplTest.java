package com.example.post.service.impl;

import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import com.example.common.dto.Result;
import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(10);

        Post post = Post.builder()
                .boardNo("1")
                .build();

        PageList<Post> result = postService.getPostList(pageRequest, post);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardNo(), is(post.getBoardNo()));
    }

    @Test
    @Transactional
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        Post postRequest = Post.builder()
                .boardNo("1")
//                .postNo("13")
                .build();

        Post result = postService.getPost(postRequest);

        assertThat(result.getTitle(), is("test13"));
        assertThat(result.getViewCnt(), is(1));
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardNo("1")
//                .postNo("13")
                .build();

        Result result = postService.deletePost(postRequest);

        assertThat(result.getCode(), is("200000"));
    }

    @Test
    @Transactional
    @DisplayName("createPost :: 정상 케이스")
    void createPost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardNo("1")
//                .title("test14")
//                .content("test14's content")
                .build();

        Post result = postService.createPost(postRequest);

        assertThat(result.getTitle(), is(postRequest.getTitle()));
    }

    @Test
    @Transactional
    @DisplayName("updatePost :: 정상 케이스")
    void updatePost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardNo("1")
//                .postNo("13")
//                .title("test13")
//                .content("test13's modified content")
                .build();

        Post result = postService.modifyPost(postRequest);

        assertThat(result.getContent(), is(postRequest.getContent()));
    }
}