package com.example.interceptor;

import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        if (token == null || "".equals(token)) {
            throw new NullPointerException(ResultType.ACCESS_TOKEN_REQUIRED.getCode());
        }
        // token 유효성 확인
        boolean isExpired = jwtUtil.isExpired(token);

        if (!isExpired) {
            throw new ExpiredJwtException(null, null, "ACCESS");
        }

        String userSeq = jwtUtil.getUserSeqFromToken(token);
        request.setAttribute("userSeq", userSeq);

        logger.info("AuthInterceptor preHandle method passed.");
        return true;
    }
}
