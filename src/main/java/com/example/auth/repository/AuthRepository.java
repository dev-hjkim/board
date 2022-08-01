package com.example.auth.repository;

import com.example.auth.model.JoinedUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {
    boolean isDuplicated(String userId);
    void signin(JoinedUser joinedUser);
    JoinedUser findUser(JoinedUser joinedUser);
}
