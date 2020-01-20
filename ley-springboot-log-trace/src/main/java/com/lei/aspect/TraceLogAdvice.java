package com.lei.aspect;

import com.lei.cache.AspectCache;
import com.lei.cache.TraceKeyCache;
import com.lei.entity.LogEntity;
import com.lei.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class TraceLogAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        AspectCache.set(getLogEntity(method, objects));
    }

    public void afterThrowing(Exception ex) {
        log.error(AspectCache.get().toString(), ex);
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        log.info(getReturnLogString(returnValue));
    }

    private HttpServletRequest getHttpRequest() {
        HttpServletRequest request = null;

        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception ex) {
            log.debug("", ex);
        }
        return request;
    }

    private LogEntity getLogEntity(Method method, Object[] objects) {
        LogEntity logEntity = new LogEntity();
        HttpServletRequest request = getHttpRequest();

        if (request != null) {
            logEntity.setHttpMethod(request.getMethod());
            logEntity.setClassMethod(method.getName());
            logEntity.setClientIP(IPUtil.getIPs(request));
            logEntity.setContentType(request.getContentType());
            logEntity.setRequestUrl(request.getRequestURI());
            logEntity.setMethodParams(getMethodArgsString(objects));
        }
        logEntity.setTraceId(TraceKeyCache.get());
        logEntity.setStartMilli(System.currentTimeMillis());

        return logEntity;
    }

    private String getMethodArgsString(Object[] args) {
        String arg = "NO-ARGS";
        if (args != null) {
            arg = Arrays.stream(args).map(Objects::toString).collect(Collectors.joining(", "));
        }
        return arg;
    }

    private String getReturnLogString(Object returnValue) {
        LogEntity logEntity = AspectCache.get();
        logEntity.setTimeCostMilli(System.currentTimeMillis() - logEntity.getStartMilli());
        logEntity.setResponseMsg(returnValue.toString());
        return logEntity.toString();
    }
}

