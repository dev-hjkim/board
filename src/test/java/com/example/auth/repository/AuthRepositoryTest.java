package com.example.auth.repository;

import com.example.auth.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthRepositoryTest {
    private AuthRepository authRepository;

    @Autowired
    public void setAuthRepositoryTest(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Test
    @DisplayName("isDuplicated :: 정상 케이스")
    void isDuplicated() {
        boolean isDuplicated = authRepository.isDuplicated("hjkim");
        assertThat(isDuplicated, is(true));
    }

    @Test
    @Transactional
    @DisplayName("signin :: 정상 케이스")
    void signin() {
        Member member = Member.builder()
                .userId("asdf")
                .password("asdf")
                .build();

        authRepository.signin(member);

        assertThat(member.getMemberNo(), notNullValue());
        assertThat(member.getRegDt(), notNullValue());
    }

    @Test
    @DisplayName("findUser :: 정상 케이스")
    void findUser() {
        Member member = Member.builder()
//                .memberNo("5")
                .build();

        Member found = authRepository.findUser(member);

        assertThat(found.getMemberNo(), is("5"));
        assertThat(found.getUserId(), is("hjkim"));
    }
}