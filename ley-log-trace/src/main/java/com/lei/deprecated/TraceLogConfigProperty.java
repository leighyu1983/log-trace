package com.lei.deprecated;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@Data
//@ConfigurationProperties(prefix = "trace-log.cut-point")
public class TraceLogConfigProperty {
    private boolean enable;
    private String pointCutExpression;
}
