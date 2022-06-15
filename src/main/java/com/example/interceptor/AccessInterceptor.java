package com.example.interceptor;

import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessInterceptor extends AuthInterceptor implements HandlerInterceptor {

    public AccessInterceptor(JwtUtil jwtUtil) {
        super(jwtUtil);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return super.preHandle(request, "Authorization", "ACCESS");
    }
}
