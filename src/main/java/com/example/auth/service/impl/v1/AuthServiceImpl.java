package com.example.auth.service.impl.v1;

import com.example.auth.model.User;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public User login(String id) {
        return authRepository.login(id);
    }
}
