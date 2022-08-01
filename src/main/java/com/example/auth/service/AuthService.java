package com.example.auth.service;

import com.example.auth.dto.UserWithToken;
import com.example.auth.model.Member;

public interface AuthService {
    Member signin(Member member);
    UserWithToken findUser(Member member);
}
