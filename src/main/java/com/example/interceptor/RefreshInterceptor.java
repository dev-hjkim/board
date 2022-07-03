package com.example.interceptor;

import com.example.common.exception.ExpiredRefreshTokenException;
import com.example.common.exception.RefreshTokenRequiredException;
import com.example.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class RefreshInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("R-Authorization");

        if (token == null || "".equals(token)) {
            throw new RefreshTokenRequiredException();
        }

        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredRefreshTokenException();
        }

        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);

        return true;
    }
}
