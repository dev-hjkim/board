package com.example.auth.service.impl;

import com.example.auth.dto.User;
import com.example.auth.dto.UserWithToken;
import com.example.auth.model.Member;
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
    void signin(){
        Member member = Member.builder()
                .userId("asdf")
                .password("asdf")
                .build();

        Member result = authService.signin(member);

        assertThat(result.getUserId(), is("asdf"));
        assertThat(result.getPassword(), is("asdf"));
        assertThat(result, instanceOf(Member.class));
    }

    @Test
    @Transactional
    @DisplayName("login :: 정상 케이스")
    void loginSuccess() {
        User user = new User("hjkim", "asdf");

        UserWithToken result = authService.login(user);

        assertThat(result.getId(), is("hjkim"));
        assertThat(result, instanceOf(UserWithToken.class));
    }

    @Test
    @DisplayName("refreshToken :: 정상 케이스")
    void refreshToken() {
        UserWithToken result = authService.refreshToken(5);

        assertThat(result.getId(), is("hjkim"));
        assertThat(result, instanceOf(UserWithToken.class));
    }
}