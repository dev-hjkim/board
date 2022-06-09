package com.example.auth.service.impl.v1;

import com.example.auth.dto.User;
import com.example.auth.model.Member;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.AuthService;
import com.example.common.dto.ResultType;
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
    public User login(Member member) {
        Member registered = authRepository.login(member);

        if (registered == null) {
            throw new NullPointerException(ResultType.UNKNOWN_USER.getCode());
        }

        return new User(registered.getUserId(),
                jwtUtil.generate(registered.getMemberNo(),"ACCESS"),
                jwtUtil.generate(registered.getMemberNo(), "REFRESH"));
    }

    @Override
    public User refreshToken(String userSeq) {
        Member registered = authRepository.findUser(userSeq);

        if (registered == null) {
            throw new NullPointerException(ResultType.UNKNOWN_USER.getCode());
        }

        return new User(registered.getUserId(),
                jwtUtil.generate(registered.getMemberNo(),"ACCESS"),
                jwtUtil.generate(registered.getMemberNo(), "REFRESH"));
    }
}
