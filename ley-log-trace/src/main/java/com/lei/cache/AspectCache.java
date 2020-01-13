package com.lei.cache;

import com.lei.entity.LogEntity;

public class AspectCache {
    private final static ThreadLocal<LogEntity> START_TIME_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(LogEntity value) { START_TIME_THREAD_LOCAL.set(value); }
    public static LogEntity get() { return START_TIME_THREAD_LOCAL.get(); }
    public static void remove() { START_TIME_THREAD_LOCAL.remove(); }
}
