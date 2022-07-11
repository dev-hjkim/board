package com.example.interceptor;

import com.example.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
abstract public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final String headerName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        checkTokenExist(token);
        setUserSeqToAttribute(request, token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    abstract protected void checkTokenExist(String token);

    private String getToken(HttpServletRequest request) {
        return request.getHeader(this.headerName);
    }

    private void setUserSeqToAttribute(HttpServletRequest request, String token) {
        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);
    }
}
