package com.lei.plugins;

import com.lei.cache.TraceKeyCache;
import com.lei.util.Constant;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix="trace-log.feign-okhttp", name="enable", havingValue = "true")
public class LogTraceFeignInterceptor {
    @Bean
    public RequestInterceptor correlationIdInterceptor() {
        return requestTemplate -> requestTemplate.header(Constant.DISTRIBUTED_TRACE_ID, TraceKeyCache.get());
    }
}
