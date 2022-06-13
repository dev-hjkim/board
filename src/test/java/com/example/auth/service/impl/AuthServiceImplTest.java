package com.example.auth.service.impl;

import com.example.auth.dto.User;
import com.example.auth.model.Member;
import com.example.auth.repository.AuthRepository;
import com.example.common.util.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    static String userId;
    static Member member;
    static Member memberBySeq;

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;


    @BeforeAll
    static void create() {
        userId = "asdf";
        member = new Member(userId, userId);
        memberBySeq = new Member("5");
    }


    @Test
    @Transactional
    @DisplayName("signin :: 정상 케이스")
    void signinSuccess(){
        Member result = authService.signin(member);

        assertThat(result.getUserId(), is(userId));
        assertThat(result.getPassword(), is(userId));
        assertThat(result, instanceOf(Member.class));
    }

    @Test
    @Transactional
    @DisplayName("findUser :: 정상 케이스")
    void findUserSuccess() {
        when(authRepository.findUser(memberBySeq)).thenReturn(new Member("hjkim", "asdf"));

        User result = authService.findUser(memberBySeq);

        assertThat(result.getId(), is("hjkim"));
        assertThat(result, instanceOf(User.class));
    }
}