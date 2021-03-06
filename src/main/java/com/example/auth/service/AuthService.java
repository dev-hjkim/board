package com.example.auth.service;

import com.example.auth.dto.User;
import com.example.auth.model.Member;

public interface AuthService {
    Member signin(Member member);
    User findUser(Member member);
}
