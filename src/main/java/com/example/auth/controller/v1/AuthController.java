package com.example.auth.controller.v1;

import com.example.auth.dto.User;
import com.example.auth.model.JoinedUser;
import com.example.auth.service.AuthService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
     * @return Member-userId, regDt, updDt
     */
    @PostMapping(value="/signin")
    public JoinedUser signin(@Valid @RequestBody User user) {
        logger.info("signin ::: {}", user);

        JoinedUser joinedUser = JoinedUser.builder()
                .userId(user.getId())
                .password(user.getPassword())
                .build();

        return authService.signin(joinedUser);
    }

    /**
     * 로그인
     *
     * @author hjkim
     * @param user-id, password
     * @return User-id, accessToken, refreshToken
     */
    @PostMapping(value="/login")
    public User login(@Valid @RequestBody User user) {
        logger.info("login ::: {}", user);

        JoinedUser joinedUser = JoinedUser.builder()
                .userId(user.getId())
                .password(user.getPassword())
                .build();

        return authService.findUser(joinedUser);
    }

    /**
     * 로그아웃
     *
     * @author hjkim
     * @param
     * @return null
     */
    @PostMapping(value="/logout")
    public Object logout() {
        logger.info("logout ::: ");

        return null;
    }

    /**
     * 토큰갱신
     *
     * @author hjkim
     * @param userSeq
     * @return User-id, accessToken, refreshToken
     */
    @GetMapping(value="/refresh")
    public User refreshToken(@RequestAttribute String userSeq) {
        logger.info("refreshToken ::: {}", userSeq);

        JoinedUser joinedUser = JoinedUser.builder()
                .memberNo(userSeq)
                .build();

        return authService.findUser(joinedUser);
    }
}
