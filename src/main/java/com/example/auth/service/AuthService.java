package com.example.auth.service;

import com.example.auth.dto.User;
import com.example.auth.model.JoinedUser;

public interface AuthService {
    JoinedUser signin(JoinedUser joinedUser);
    User findUser(JoinedUser joinedUser);
}
