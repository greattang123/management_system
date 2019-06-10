package com.example.management_system;

import com.example.management_system.interceptor.AdminInterceptor;
import com.example.management_system.interceptor.LoginInterceptor;
import com.example.management_system.interceptor.SuperAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor li;
    @Autowired
    private AdminInterceptor ai;
    @Autowired
    private SuperAdminInterceptor sai;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( li)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        registry.addInterceptor( ai)
                .addPathPatterns("/api/admin");
        registry.addInterceptor(sai)
                .addPathPatterns("/api/superAdmin");
    }
}
