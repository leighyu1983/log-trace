package com.lei.plugins;

import com.lei.cache.TraceKeyCache;
import com.lei.util.Constant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


@Configuration
@ConditionalOnProperty(prefix="trace-log.rest-template", name="enable", havingValue = "true")
public class LogTraceRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        httpRequest.getHeaders().add(Constant.DISTRIBUTED_TRACE_ID, TraceKeyCache.get());
        return execution.execute(httpRequest, body);
    }
}