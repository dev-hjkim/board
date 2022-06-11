package com.example.common.config;

import com.example.interceptor.AuthInterceptor;
import com.example.interceptor.RefreshInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final RefreshInterceptor refreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/v1/auth/signin", "/v1/auth/login", "/v1/auth/refresh");
        registry.addInterceptor(refreshInterceptor)
                .addPathPatterns("/v1/auth/refresh");
    }

//    private final JwtUtil jwtUtil;
//
//    @Bean
//    public FilterRegistrationBean<OncePerRequestFilter> authFilter() {
//        return new FilterRegistrationBean<>(new AuthFilter(jwtUtil));
//    }
}
