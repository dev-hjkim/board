package com.example.interceptor;

import com.example.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
abstract public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        checkTokenExist(token);
        checkTokenExpired(token);
        setUserSeqToAttribute(request, token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    abstract protected String getToken(HttpServletRequest request);
    abstract protected void checkTokenExist(String token);

    protected void checkTokenExpired(String token) throws ExpiredJwtException {
        jwtUtil.isExpired(token);
    }

    protected void setUserSeqToAttribute(HttpServletRequest request, String token) {
        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);
    }
}
