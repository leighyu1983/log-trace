package com.lei.external;

import com.lei.cache.TraceKeyCache;

public class LogTraceCacheFacade {
    /**
     * Get current traceId in current thread
     * @return Trace id(uuid)
     */
    public static String getTraceId() {
        return TraceKeyCache.get();
    }
}
