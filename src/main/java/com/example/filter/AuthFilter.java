package com.example.filter;

import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
            // Request Header 에 Authorization 이 존재하지 않을 때
            if (request.getHeader("Authorization") == null) {
                try {
                    response = setHttpServletResponse(response, ResultType.ACCESS_TOKEN_REQUIRED);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Request Header 에서 token 문자열 받아오기
                String token = request.getHeader("Authorization");
                if (jwtUtil.isExpired(token)) {
                    try {
                        response = setHttpServletResponse(response, ResultType.EXPIRED_ACCESS_TOKEN);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                String userSeq = jwtUtil.getUserSeqFromToken(token);
            }
            chain.doFilter(request, response);
        }
    }

    private HttpServletResponse setHttpServletResponse(HttpServletResponse response, ResultType resultType) throws IOException {
        Result result = new Result(resultType);
        response.setStatus(result.parseHttpCode().value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(result));

        return response;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
