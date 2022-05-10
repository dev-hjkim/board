package com.example.common.config;

import com.example.common.util.JwtUtil;
import com.example.filter.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements WebMvcConfigurer {

    private final JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> authFilter() {
        return new FilterRegistrationBean<>(new AuthFilter(jwtUtil));
    }
}
