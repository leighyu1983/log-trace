package com.lei.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "startMilli", includeFieldNames = true)
public class LogEntity {
    private String clientIP;
    private String requestUrl;
    private String httpMethod;
    private String contentType;
    private String classMethod;
    private String methodParams;
    private String responseMsg;
    private long timeCostMilli;
    private String traceId;

    private long startMilli;
}
