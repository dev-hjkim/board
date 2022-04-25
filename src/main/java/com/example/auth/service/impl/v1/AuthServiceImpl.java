package com.example.auth.service.impl.v1;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;
import com.example.auth.mapper.AuthMapper;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;

    @Override
    public User login(Login login) {
        return authMapper.login(login);
    }
}
