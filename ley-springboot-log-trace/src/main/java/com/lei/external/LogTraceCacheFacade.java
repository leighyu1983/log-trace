package com.lei.external;

import com.lei.cache.TraceKeyCache;

public class LogTraceCacheFacade {
    public static String getTraceId() {
        return TraceKeyCache.get();
    }
}
