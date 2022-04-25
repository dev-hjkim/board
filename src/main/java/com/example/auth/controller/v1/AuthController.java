package com.example.auth.controller.v1;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;
import com.example.auth.service.AuthService;
import com.example.common.code.AuthCode;
import com.example.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    @PostMapping(value="/login")
    public ResponseEntity<Object> Login(@RequestBody Login login) {
        Response response;

        User user = authService.login(login);

        if (user == null) {
            response = new Response(false, AuthCode.AU002);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else {
            response = new Response(true, AuthCode.AU001, user);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
