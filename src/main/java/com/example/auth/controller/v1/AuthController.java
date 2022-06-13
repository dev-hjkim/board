package com.example.auth.controller.v1;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;
import com.example.auth.model.Member;
import com.example.auth.service.AuthService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;


    /**
     * 회원가입
     *
     * @author hjkim
     * @param user-id, password
     * @return member-userId, regDt, updDt
     */
    @PostMapping(value="/signin")
    public Member signin(@Valid @RequestBody User user) {
        logger.info("signin ::: {}", user);

        Member member = new Member(user.getId(), user.getPassword());
        return authService.signin(member);
    }

    /**
     * 로그인
     *
     * @author hjkim
     * @param login-id, password
     * @return user-id, accessToken, refreshToken
     */
    @PostMapping(value="/login")
    public User login(@Valid @RequestBody Login login) {
        logger.info("login ::: {}", login);

        Member member = new Member(login.getId(), login.getPassword());
        return authService.login(member);
    }
}
