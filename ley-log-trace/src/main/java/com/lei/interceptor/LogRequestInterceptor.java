package com.lei.interceptor;

import com.lei.cache.AspectCache;
import com.lei.util.Constant;
import com.lei.cache.TraceKeyCache;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Configuration
public class LogRequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(Constant.DISTRIBUTED_TRACE_ID);
        traceId = Optional.ofNullable(traceId).orElse(UUID.randomUUID().toString());
        TraceKeyCache.set(traceId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        TraceKeyCache.remove();
        AspectCache.remove();
    }
}
