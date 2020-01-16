package com.lei.config;


import com.lei.interceptor.LogTraceRequestInterceptor;
import com.lei.plugins.LogTraceRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;


@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Autowired
    private LogTraceRequestInterceptor logTraceRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logTraceRequestInterceptor).addPathPatterns("/**");
    }
}
