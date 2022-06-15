package com.example.interceptor;

import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RefreshInterceptor extends AuthInterceptor implements HandlerInterceptor {
    public RefreshInterceptor(JwtUtil jwtUtil) {
        super(jwtUtil);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return super.preHandle(request, "R-Authorization", "REFRESH");
    }
}
