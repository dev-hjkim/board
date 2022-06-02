package com.example.auth.service.impl.v1;

import com.example.auth.model.Member;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.AuthService;
import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
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
        authRepository.signin(member);
        return member;
    }

    @Override
    public Member login(Member member) {
        Member registered = authRepository.login(member);

        if (registered == null) {
            throw new NullPointerException(ResultType.UNKNOWN_USER.getCode());
        }
        registered.setAccessToken(jwtUtil.generate(registered.getMemberNo(),"ACCESS"));
        registered.setRefreshToken(jwtUtil.generate(registered.getMemberNo(), "REFRESH"));

        return registered;
    }
}
