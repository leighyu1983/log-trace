package com.lei.deprecated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


public class TraceLogAfterThrownAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

    }
}
