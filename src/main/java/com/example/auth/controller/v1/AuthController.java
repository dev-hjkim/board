package com.example.auth.controller.v1;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;
import com.example.auth.model.Member;
import com.example.auth.service.AuthService;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
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


    @PostMapping(value="/signin")
    public Result signin(@Valid @RequestBody User user) {
        Member member = new Member(user.getId(), user.getPassword(), user.getName());
        Member registered = authService.signin(member);
        return new Result(ResultType.OK, registered);
    }

    @PostMapping(value="/login")
    public Result login(@Valid @RequestBody Login login) {
        Member member = new Member(login.getId(), login.getPassword());
        Member loginOn = authService.login(member);

        if (loginOn == null) {
            return new Result(ResultType.UNKNOWN_USER);
        } else {
            return new Result(ResultType.OK, loginOn);
        }
    }
}
