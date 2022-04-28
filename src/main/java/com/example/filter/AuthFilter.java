package com.example.filter;

import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import com.example.common.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private static JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Request Header 에 Authorization 이 존재하지 않을 때
        if (httpRequest.getHeader("Authorization") == null) {
            try {
                httpResponse = setHttpServletResponse(httpResponse, ResultType.ACCESS_TOKEN_REQUIRED);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Request Header 에서 token 문자열 받아오기
        String token = httpRequest.getHeader("Authorization");
        if (jwtUtil.isExpired(token)) {
            try {
                httpResponse = setHttpServletResponse(httpResponse, ResultType.EXPIRED_ACCESS_TOKEN);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String userSeq = jwtUtil.getUserSeqFromToken(token);

        chain.doFilter(httpRequest, httpResponse);
    }

    private HttpServletResponse setHttpServletResponse(HttpServletResponse response, ResultType resultType) throws IOException {
        Result result = new Result(resultType);
        response.setStatus(result.getHttpCode().value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getOutputStream()
                .print(new ObjectMapper().writeValueAsString(result));
        return response;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
