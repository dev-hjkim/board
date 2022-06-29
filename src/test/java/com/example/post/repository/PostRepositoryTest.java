package com.example.post.repository;

import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Test
    @DisplayName("getTotalCount :: 정상 케이스")
    void getTotalCount() {
        int totalCount = postRepository.getTotalCount("AAA");

        assertThat(totalCount, is(2));
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        Post post = Post.builder()
                .boardCd("AAA")
                .startPage(0)
                .pageSize(10)
                .build();

        List<Post> postList = postRepository.getPostList(post);
        assertThat(postList.get(0).getBoardCd(), is("AAA"));
        assertThat(postList.size(), is(2));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        Post postRequest = Post.builder()
                .boardCd("AAA")
                .boardNo("13")
                .build();

        Post post = postRepository.getPost(postRequest);
        assertThat(post.getTitle(), is("test13"));
    }

    @Test
    @Transactional
    @DisplayName("updateViewCount :: 정상 케이스")
    void updateViewCount() {
        Post postRequest = Post.builder()
                .boardCd("AAA")
                .boardNo("13")
                .title("test13")
                .content("test13's content")
                .memberNo("5")
                .userId("hjkim")
                .viewCnt(1)
                .replyCnt(0)
                .build();

        postRepository.updateViewCount(postRequest);
        assertThat(postRequest.getViewCnt(), is(1));
        assertThat(postRequest.getBoardCd(), is("AAA"));
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardCd("AAA")
                .boardNo("13")
                .build();

        postRepository.deletePost(postRequest);

        Post post = postRepository.getPost(postRequest);
        assertThat(post, nullValue());
    }

    @Test
    @Transactional
    @DisplayName("insertPost :: 정상 케이스")
    void insertPost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardCd("AAA")
                .title("test14")
                .content("test14's content")
                .build();

        postRepository.insertPost(postRequest);
        assertThat(postRequest.getTitle(), is("test14"));
    }

    @Test
    @Transactional
    @DisplayName("updatePost :: 정상 케이스")
    void updatePost() {
        Post postRequest = Post.builder()
                .memberNo("5")
                .boardCd("AAA")
                .boardNo("13")
                .title("test13")
                .content("test13's modified content")
                .build();

        postRepository.updatePost(postRequest);
        assertThat(postRequest.getContent(), is("test13's modified content"));
    }
}