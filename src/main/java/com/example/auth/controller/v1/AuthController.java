package com.example.auth.controller.v1;

import com.example.auth.model.User;
import com.example.auth.service.AuthService;
import com.example.common.dto.ResponseType;
import com.example.common.dto.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        User user = authService.login(login.getId());

        if (user == null) {
            response = new Response(ResponseType.UNKNOWN_USER);
            return new ResponseEntity<>(response, response.getHttpCode());
        } else {
            response = new Response(ResponseType.OK, user);
        }

        return new ResponseEntity<>(response, response.getHttpCode());
    }

    @Getter
    @Setter
    public class Login {
        String id;
        String password;
    }
}
