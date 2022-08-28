package com.example.post.repository;

import com.example.common.dto.PageRequest;
import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        int totalCount = postRepository.getTotalCount(1);

        assertThat(totalCount, is(2));
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(10);

        List<Post> postList = postRepository.getPostList(pageRequest, 1);
        assertThat(postList.get(0).getTitle(), is("test1"));
        assertThat(postList.size(), is(2));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        Post post = postRepository.getPost(13);
        assertThat(post.getTitle(), is("test13"));
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() {
        postRepository.deletePost(5);

        Post post = postRepository.getPost(5);
        assertThat(post, nullValue());
    }

    @Test
    @Transactional
    @DisplayName("insertPost :: 정상 케이스")
    void insertPost() {
        Post postRequest = Post.builder()
                .memberNo(5)
                .boardNo(1)
                .build();

        postRequest.setTitle("test14");
        postRequest.setContent("test14's content");

        postRepository.insertPost(postRequest);
        assertThat(postRequest.getPostNo(), instanceOf(Long.TYPE));
    }

    @Test
    @Transactional
    @DisplayName("updatePost :: 정상 케이스")
    void updatePost() {
        Post postRequest = postRepository.getPost(13);
        postRequest.setTitle("test13");
        postRequest.setContent("test13's modified content");

        postRepository.updatePost(postRequest);
        assertThat(postRequest.getPostNo(), is(13l));
    }
}