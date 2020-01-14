package com.lei.interceptor;

import com.lei.cache.TraceKeyCache;
import com.lei.util.Constant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix="trace-log.feign", name="enable", havingValue = "true")
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(Constant.DISTRIBUTED_TRACE_ID, TraceKeyCache.get());
    }
}
