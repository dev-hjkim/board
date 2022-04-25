package com.example.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {

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
            httpResponse.setStatus(403);
        }

        // Request Header 에서 token 문자열 받아오기
        String token = httpRequest.getHeader("Authorization");

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
