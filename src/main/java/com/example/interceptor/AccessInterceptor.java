package com.example.interceptor;

import com.example.common.exception.AccessTokenRequiredException;
import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessInterceptor extends AuthInterceptor {

    public AccessInterceptor(JwtUtil jwtUtil) {
        super(jwtUtil);
    }

    @Override
    protected String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    @Override
    protected void checkTokenExist(String token) {
        if (token == null || "".equals(token)) {
            throw new AccessTokenRequiredException();
        }
    }
}
