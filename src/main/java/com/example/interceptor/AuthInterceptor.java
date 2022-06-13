package com.example.interceptor;

import com.example.common.exception.ExpiredTokenException;
import com.example.common.exception.TokenRequiredException;
import com.example.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class AuthInterceptor {

    private final JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, String key, String tokenType) {
        String token = getToken(request, key, tokenType);

        checkExpired(token, tokenType);

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

    private void checkExpired(String token, String tokenType) {
        boolean isExpired = jwtUtil.isExpired(token);

        if (isExpired) {
            throw new ExpiredTokenException(tokenType);
        }
    }

    private void setRequestAttribute(HttpServletRequest request, String token) {
        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);
    }
}
