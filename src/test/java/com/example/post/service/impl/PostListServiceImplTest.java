package com.example.post.service.impl;

import com.example.post.dto.PostList;
import com.example.post.model.PostRequest;
import com.example.post.model.Post;
import com.example.post.model.PostPageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        PostPageRequest postrequest = new PostPageRequest();
        postrequest.setBoardName("AAA");

        PostList result = postService.getPostList(postrequest);

        assertThat(result.getTotalCount(), is(2));
        assertThat(result.getTotalPage(), is(1));
        assertThat(result.getList().get(0).getBoardCd(), is("AAA"));
    }

    @Test
    @DisplayName("getPost :: 정상 케이스")
    void getPost() {
        PostRequest postrequest = new PostRequest("AAA", "13");

        Post result = postService.getPost(postrequest);

        assertThat(result.getTitle(), is("test13"));
    }
}