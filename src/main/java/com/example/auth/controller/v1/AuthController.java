package com.example.auth.controller.v1;

import com.example.auth.model.Member;
import com.example.auth.service.AuthService;
import com.example.common.dto.ResultType;
import com.example.common.dto.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;


    @PostMapping(value="/signin")
    public ResponseEntity<Object> signin(@Valid @RequestBody User user) {
        Result result;
        Member member = new Member(user.getId(), user.getPassword(), user.getName());
        Member registered = authService.signin(member);
        result = new Result(ResultType.OK, registered);
        return new ResponseEntity<>(result, result.parseHttpCode());
    }

    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@Valid @RequestBody Login login) {
        Result result;
        Member member = new Member(login.getId(), login.getPassword());
        Member loginOn = authService.login(member);

        if (loginOn == null) {
            result = new Result(ResultType.UNKNOWN_USER);
        } else {
            result = new Result(ResultType.OK, loginOn);
        }

        return new ResponseEntity<>(result, result.parseHttpCode());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Login {
        @NotEmpty
        String id;
        @NotEmpty
        String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {
        @NotEmpty
        String id;
        @NotEmpty
        String password;
        @NotEmpty
        String name;
    }
}
