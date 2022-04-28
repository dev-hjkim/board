package com.example.auth.controller.v1;

import com.example.auth.model.User;
import com.example.auth.service.AuthService;
import com.example.common.dto.ResultType;
import com.example.common.dto.Result;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    @PostMapping(value="/login")
    public ResponseEntity<Object> Login(@Valid @RequestBody Login login) {
        Result result;

        User user = authService.login(login.getId());

        if (user == null) {
            result = new Result(ResultType.UNKNOWN_USER);
            return new ResponseEntity<>(result, result.getHttpCode());
        } else {
            result = new Result(ResultType.OK, user);
        }

        return new ResponseEntity<>(result, result.getHttpCode());
    }

    @Getter
    @Setter
    public static class Login {
        @NotEmpty
        String id;
        @NotEmpty
        String password;
    }
}
