package com.example.auth.repository;

import com.example.auth.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {
    User login(String id);
}
