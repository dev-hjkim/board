package com.example.auth.service;

import com.example.auth.model.Member;

public interface AuthService {
    Member signin(Member member);
    Member login(String id);
}
