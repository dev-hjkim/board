package com.example.auth.repository;

import com.example.auth.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRepository {
    boolean isDuplicated(String userId);
    void signin(Member member);
    Member login(Member member);
}
