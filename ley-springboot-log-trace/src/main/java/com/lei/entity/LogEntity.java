package com.lei.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
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
