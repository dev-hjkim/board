package com.example.auth.service.impl;

import com.example.auth.dto.User;
import com.example.auth.model.JoinedUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class AuthServiceImplTest {
    private AuthServiceImpl authService;

    @Autowired
    public void setAuthServiceImplTest(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @Test
    @Transactional
    @DisplayName("signin :: 정상 케이스")
    void signinSuccess(){
        JoinedUser joinedUser = JoinedUser.builder()
                .userId("asdf")
                .password("asdf")
                .build();

        JoinedUser result = authService.signin(joinedUser);

        assertThat(result.getUserId(), is("asdf"));
        assertThat(result.getPassword(), is("asdf"));
        assertThat(result, instanceOf(JoinedUser.class));
    }

    @Test
    @DisplayName("findUser :: 정상 케이스")
    void findUserSuccess() {
        JoinedUser joinedUser = JoinedUser.builder()
                .memberNo("5")
                .build();

        User result = authService.findUser(joinedUser);

        assertThat(result.getId(), is("hjkim"));
        assertThat(result, instanceOf(User.class));
    }
}