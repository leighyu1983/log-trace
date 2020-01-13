package com.lei.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;

@Configuration
@EnableConfigurationProperties(TraceLogConfigProperty.class)
@ConditionalOnProperty(prefix="trace-log", value="enabled", matchIfMissing = true)
public class TraceLogAutoConfig extends AnyNestedCondition {
    @Autowired
    private TraceLogConfigProperty traceLogConfigProperties;

    public TraceLogAutoConfig() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @Bean("traceLogConfigBean")
    @ConditionalOnMissingBean(TraceLogConfigBean.class)
    public TraceLogConfigBean logConfigBean() {
        TraceLogConfigBean traceLogConfigBean = new TraceLogConfigBean();
        traceLogConfigBean.setControllerPointCutExpression(
                Optional.ofNullable(traceLogConfigProperties.getPointCutExpression()).orElse("execution(* com..*.*Controller.*(..))"));
        return traceLogConfigBean;
    }
}
