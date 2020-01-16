package com.lei.deprecated;

import com.lei.cache.TraceKeyCache;
import com.lei.entity.LogEntity;
import com.lei.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


//@Slf4j
//@Configuration
public class MethodInterceptorProcessor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object proceed = null;
        HttpServletRequest request = null;
        long startTime = System.currentTimeMillis();

        try {
            request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            proceed = invocation.proceed();
            return proceed;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private LogEntity getLogEntity(HttpServletRequest request, MethodInvocation invocation, long timeCost) {
        LogEntity logEntity = new LogEntity();
        if(request != null) {
            logEntity.setHttpMethod(request.getMethod());
            logEntity.setClassMethod(invocation.getMethod().getName());
            logEntity.setClientIP(IPUtil.getIPs(request));
            logEntity.setContentType(request.getContentType());
            logEntity.setRequestUrl(request.getRequestURI());
            logEntity.setMethodParams(getMethodArgsString(invocation.getArguments()));
        }
        logEntity.setTraceId(TraceKeyCache.get());
        logEntity.setTimeCostMilli(timeCost);

        return logEntity;
    }

    private String getMethodArgsString(Object[] args) {
        String arg = "NO-ARGS";
        if (args != null) {
            arg = Arrays.stream(args).map(Objects::toString).collect(Collectors.joining(", "));
        }
        return arg;
    }
}
