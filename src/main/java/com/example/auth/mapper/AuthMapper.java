package com.example.auth.mapper;

import com.example.auth.dto.Login;
import com.example.auth.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    User login(Login login);
}
