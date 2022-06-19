package com.example.post.repository;

import com.example.post.model.PostItem;
import com.example.post.model.PostPage;
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
class PostRepositoryTest {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Test
    @DisplayName("getTotalList :: 정상 케이스")
    void getTotalList() {
        int totalCount = postRepository.getTotalList("AAA");

        assertThat(totalCount, is(1));
    }

    @Test
    @DisplayName("getPostList :: 정상 케이스")
    void getPostList() {
        PostPage postPage = new PostPage();
        postPage.setBoardName("AAA");

        List<PostItem> postItemList = postRepository.getPostList(postPage);
        assertThat(postItemList.get(0).getBoardCd(), is("AAA"));
        assertThat(postItemList.size(), is(1));
    }
}