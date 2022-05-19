package com.example.interceptor;

import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || "".equals(token)) {
            setHttpServletResponse(response, ResultType.ACCESS_TOKEN_REQUIRED);
        } else {
            // TODO :: Exception 정리(JwtUtil 쪽으로?)
            // token 유효성 확인
            try {
                jwtUtil.isExpired(token);
            } catch (ExpiredJwtException ex) {
                ex.printStackTrace();
                setHttpServletResponse(response, ResultType.EXPIRED_ACCESS_TOKEN);
            } catch (MalformedJwtException ex) {
                ex.printStackTrace();
                setHttpServletResponse(response, ResultType.INVALID_TOKEN);
            } catch (JwtException ex) { // TODO :: 여러 error case를 전부 처리할지? 발생할 것 같은 에러만 처리할지?
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String userSeq = null;
            try {
                userSeq = jwtUtil.getUserSeqFromToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("userSeq", userSeq);
            System.out.println(userSeq);
            return true;
        }
        return false;
    }

    private void setHttpServletResponse(HttpServletResponse response, ResultType resultType) throws IOException {
        Result result = new Result(resultType);
        response.setStatus(result.parseHttpCode().value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String content = new ObjectMapper().writeValueAsString(result);
        response.setContentLength(content.length());
        response.getWriter()
                .write(content);
    }
}
