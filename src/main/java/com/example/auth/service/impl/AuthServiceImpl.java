package com.example.auth.service.impl;

import com.example.auth.dto.User;
import com.example.auth.dto.UserWithToken;
import com.example.auth.model.Member;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.AuthService;
import com.example.common.exception.UserNotFoundException;
import com.example.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public Member signin(Member member) {
        boolean isDuplicated = authRepository.isDuplicated(member.getUserId());

        if (isDuplicated) {
            throw new DuplicateKeyException("userId");
        }

        authRepository.signin(member);
        return member;
    }

    @Override
    public UserWithToken login(User user, Member member) {
        if (!user.getPassword().equals(member.getPassword())) {
            throw new UserNotFoundException();
        }

        return generateToken(member);
    }

    @Override
    public UserWithToken generateToken(Member member) {
        return new UserWithToken(member.getUserId(),
                jwtUtil.generate(member.getMemberNo(), "ACCESS"),
                jwtUtil.generate(member.getMemberNo(), "REFRESH"));
    }
}
