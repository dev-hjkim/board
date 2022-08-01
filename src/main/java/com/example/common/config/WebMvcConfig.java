package com.example.common.config;

import com.example.interceptor.AccessInterceptor;
import com.example.interceptor.RefreshInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AccessInterceptor accessInterceptor;
    private final RefreshInterceptor refreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor)
                .excludePathPatterns("/v1/auth/signin", "/v1/auth/login", "/v1/auth/refresh",
                        "/swagger-ui/**", "/swagger-resources/**", "/error",
                        "/v2/api-docs");
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
