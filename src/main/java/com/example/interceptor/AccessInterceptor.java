package com.example.interceptor;

import com.example.common.exception.AccessTokenRequiredException;
import com.example.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isEmpty;

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
        if (isEmpty(token)) {
            throw new AccessTokenRequiredException();
        }
    }
}
