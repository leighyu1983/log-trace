package com.lei.deprecated;

import com.lei.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;

//@Configuration
//@EnableConfigurationProperties(TraceLogConfigProperty.class)
public class TraceLogAutoConfig extends AnyNestedCondition {
    //@Autowired
    //private TraceLogConfigProperty traceLogConfigProperties;

    public TraceLogAutoConfig() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

//    @Bean("traceLogConfigBean")
//    public TraceLogConfigBean logConfigBean() {
//        TraceLogConfigBean traceLogConfigBean = new TraceLogConfigBean();
//        traceLogConfigBean.setControllerPointCutExpression(
//                Optional.ofNullable(traceLogConfigProperties.getPointCutExpression())
//                        .orElse(Constant.DEFAULT_POINT_CUT_EXPRESSION));
//        return traceLogConfigBean;
//    }
}
