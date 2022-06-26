package com.example.post.repository;

import com.example.post.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostListRepositoryTest {

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
        Post post = new Post("AAA", 0, 10);

        List<Post> postList = postRepository.getPostList(post);
        assertThat(postList.get(0).getBoardCd(), is("AAA"));
        assertThat(postList.size(), is(2));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        Post postRequest = new Post("AAA", "13");

        Post post = postRepository.getPost(postRequest);
        assertThat(post.getTitle(), is("test13"));
    }

    @Test
    @Transactional
    @DisplayName("deletePost :: 정상 케이스")
    void deletePost() {
        Post postRequest = new Post("AAA", "13");
        postRepository.deletePost(postRequest);

        Post post = postRepository.getPost(postRequest);
        assertThat(post, nullValue());
    }

    @Test
    @Transactional
    @DisplayName("insertPost :: 정상 케이스")
    void insertPost() {
        Post postRequest = new Post("AAA", "test14",
                "test14's content", "5");

        postRepository.insertPost(postRequest);
        assertThat(postRequest.getTitle(), is("test14"));
    }

    @Test
    @Transactional
    @DisplayName("updatePost :: 정상 케이스")
    void updatePost() {
        Post postRequest = new Post("AAA", "13", "test13",
                "test13's modified content", "5");

        postRepository.updatePost(postRequest);
        assertThat(postRequest.getContent(), is("test13's modified content"));
    }
}