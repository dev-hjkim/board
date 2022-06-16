package com.example.interceptor;

import com.example.common.exception.ExpiredTokenException;
import com.example.common.exception.TokenRequiredException;
import com.example.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class AuthInterceptor {

    private final JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, String key, String tokenType) {
        String token = getToken(request, key, tokenType);

        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredTokenException(tokenType);
        }

        setRequestAttribute(request, token);

        return true;
    }

    private String getToken(HttpServletRequest request, String key, String tokenType) {
        String token = request.getHeader(key);

        if (token == null || "".equals(token)) {
            throw new TokenRequiredException(tokenType);
        }
        return token;
    }

    private void setRequestAttribute(HttpServletRequest request, String token) {
        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);
    }
}
