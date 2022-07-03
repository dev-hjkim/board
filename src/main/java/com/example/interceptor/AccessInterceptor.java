package com.example.interceptor;

import com.example.common.exception.AccessTokenRequiredException;
import com.example.common.exception.ExpiredAccessTokenException;
import com.example.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AccessInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        if (token == null || "".equals(token)) {
            throw new AccessTokenRequiredException();
        }

        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredAccessTokenException();
        }

        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);

        return true;
    }
}
