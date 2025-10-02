package com.votrihieu.web26t9.config;

import com.votrihieu.web26t9.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/user/**") // Áp dụng interceptor cho các URL này
                .excludePathPatterns("/login", "/logout", "/graphql", "/vendor/**", "/css/**", "/js/**", "/images/**"); // Loại trừ các URL này
    }
}