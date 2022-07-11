package com.example.interceptor;

import com.example.common.exception.RefreshTokenRequiredException;
import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RefreshInterceptor extends AuthInterceptor {

    public RefreshInterceptor(JwtUtil jwtUtil) {
        super(jwtUtil);
    }

    @Override
    protected String getToken(HttpServletRequest request) {
        return request.getHeader("R-Authorization");
    }

    @Override
    protected void checkTokenExist(String token) {
        if (token == null || "".equals(token)) {
            throw new RefreshTokenRequiredException();
        }
    }
}
