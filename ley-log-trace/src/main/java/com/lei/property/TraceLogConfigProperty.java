package com.lei.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "trace-log")
public class TraceLogConfigProperty {
    private String pointCutExpression;
}
