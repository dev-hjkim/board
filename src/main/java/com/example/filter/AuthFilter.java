package com.example.filter;

import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();
        if (path.startsWith("/v1/auth/")) {
            chain.doFilter(request, response);
        } else {
            // Request Header 에서 token 문자열 받아오기
            String token = request.getHeader("Authorization");

            // Request Header 에 Authorization 이 존재하지 않을 때
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
                System.out.println(userSeq);
//                chain.doFilter(request, response);
            }
        }
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

    @Override
    public void destroy() {
        super.destroy();
    }
}
