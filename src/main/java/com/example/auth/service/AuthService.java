package com.example.auth.service;

import com.example.auth.dto.User;
import com.example.auth.dto.UserWithToken;
import com.example.auth.model.Member;

public interface AuthService {
    Member signin(Member member);
    UserWithToken login(User user, Member member);
    UserWithToken generateToken(Member member);
}
