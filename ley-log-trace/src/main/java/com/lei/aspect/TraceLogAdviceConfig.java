package com.lei.aspect;

import com.lei.property.TraceLogConfigBean;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceLogAdviceConfig {
    @Autowired private TraceLogConfigBean traceLogConfigBean;
    @Autowired private TraceLogAdvice traceLogAdvice;

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceLogConfigBean.getControllerPointCutExpression());

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(traceLogAdvice);

        advisor.setOrder(1);

        return advisor;
    }
}
