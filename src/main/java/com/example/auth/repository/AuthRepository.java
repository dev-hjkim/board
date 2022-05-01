package com.example.auth.repository;

import com.example.auth.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {
    void signin(Member member);
    Member login(String id);
}
