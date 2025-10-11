package com.qk.management.web.config;

import com.qk.management.web.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 14:08
 * @Description: WebConfig
 */
@Configuration
@SuppressWarnings("all")
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(myInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/login");
    }
}
