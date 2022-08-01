package com.example.auth.service;

import com.example.auth.dto.UserWithToken;
import com.example.auth.model.JoinedUser;

public interface AuthService {
    JoinedUser signin(JoinedUser joinedUser);
    UserWithToken findUser(JoinedUser joinedUser);
}
