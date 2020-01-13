package com.lei.config;

import com.lei.interceptor.LogRequestInterceptor;
import com.lei.property.TraceLogConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Autowired
    private LogRequestInterceptor logRequestInterceptor;
    @Autowired
    private TraceLogConfigBean traceLogConfigBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logRequestInterceptor).addPathPatterns("/**");
    }
}
