package com.example.auth.service.impl.v1;

import com.example.auth.model.Member;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.AuthService;
import com.example.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Member signin(Member member) {
        authRepository.signin(member);
        return member;
    }

    @Override
    public Member login(Member member) {
        Member registered = authRepository.login(member);
        if (registered != null) {
            registered.setAccessToken(jwtUtil.generate(registered.getSeq(),"ACCESS"));
            registered.setRefreshToken(jwtUtil.generate(registered.getSeq(), "REFRESH"));
        }
        return registered;
    }
}
