package com.example.post.repository;

import com.example.post.model.Post;
import com.example.post.model.PostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostListRepositoryTest {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Test
    @DisplayName("getTotalList :: 정상 케이스")
    void getTotalList() {
        int totalCount = postRepository.getTotalList("AAA");

        assertThat(totalCount, is(2));
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        PostRequest postRequest = new PostRequest();
        postRequest.setBoardName("AAA");

        List<Post> postList = postRepository.getPostList(postRequest);
        assertThat(postList.get(0).getBoardCd(), is("AAA"));
        assertThat(postList.size(), is(2));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        PostRequest postRequest = new PostRequest();
        postRequest.setBoardName("AAA");
        postRequest.setPostSeq("13");

        Post post = postRepository.getPost(postRequest);
        assertThat(post.getTitle(), is("test13"));
    }
}