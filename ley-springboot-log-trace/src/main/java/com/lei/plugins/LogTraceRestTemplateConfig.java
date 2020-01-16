package com.lei.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(prefix="trace-log.rest-template", name="enable", havingValue = "true")
public class LogTraceRestTemplateConfig {

    @Autowired(required = false)
    private RestTemplate restTemplate;
    @Autowired
    private LogTraceRestTemplateInterceptor logTraceRestTemplateInterceptor;

    @PostConstruct
    public void appendRestTemplate() {
        if(restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        restTemplate.getInterceptors().add(logTraceRestTemplateInterceptor);
    }
}
