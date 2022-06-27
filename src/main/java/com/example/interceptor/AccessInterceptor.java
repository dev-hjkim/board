package com.example.interceptor;

import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessInterceptor extends AuthInterceptor {

    public AccessInterceptor(JwtUtil jwtUtil) {
        super(jwtUtil);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return super.preHandle(request, "Authorization", "ACCESS");
    }
}
