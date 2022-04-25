package com.example.auth.service;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;

public interface AuthService {
    User login(Login login);
}
