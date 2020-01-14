package com.lei.interceptor;

import com.lei.cache.TraceKeyCache;
import com.lei.util.Constant;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("who-me-i", "test overwrite");
    }
}
