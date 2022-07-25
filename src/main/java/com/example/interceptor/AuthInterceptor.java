package com.example.interceptor;

import com.example.common.exception.ExpiredTokenException;
import com.example.common.util.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
abstract public class AuthInterceptor implements HandlerInterceptor {

    private static final String USER_SEQ_ATTRIBUTE_KEY = "userSeq";

    private final JwtUtil jwtUtil;

    @Getter
    @Setter
    protected String token;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkTokenExist();
        checkTokenExpired();
        setUserSeqToAttribute(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    abstract protected void checkTokenExist();

    protected String getTokenFromHeader(HttpServletRequest request, String headerName) {
        return request.getHeader(headerName);
    }

    private void checkTokenExpired() {
        if (jwtUtil.isExpired(this.token)) {
            throw new ExpiredTokenException();
        }
    }

    private void setUserSeqToAttribute(HttpServletRequest request) {
        String userSeq = jwtUtil.getUserSeqFromToken(this.token);
        request.setAttribute(USER_SEQ_ATTRIBUTE_KEY, userSeq);
    }
}
