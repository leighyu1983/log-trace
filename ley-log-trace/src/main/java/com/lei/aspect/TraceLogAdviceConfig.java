package com.lei.aspect;

import com.lei.util.Constant;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@ConditionalOnProperty(prefix="trace-log.cut-point", name="enable", havingValue = "true")
@Configuration
public class TraceLogAdviceConfig {
    @Autowired private TraceLogAdvice traceLogAdvice;

    @Value("${trace-log.cut-point.expression}")
    private String pointCutExpression;

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                StringUtils.isEmpty(pointCutExpression) ? Constant.DEFAULT_POINT_CUT_EXPRESSION : pointCutExpression);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(traceLogAdvice);
        advisor.setOrder(1);

        return advisor;
    }
}
