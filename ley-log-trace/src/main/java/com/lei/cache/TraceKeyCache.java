package com.lei.cache;

public class TraceKeyCache {
    private final static ThreadLocal<String> TRACE_KEY_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String value) { TRACE_KEY_THREAD_LOCAL.set(value); }
    public static String get() { return TRACE_KEY_THREAD_LOCAL.get(); }
    public static void remove() { TRACE_KEY_THREAD_LOCAL.remove(); }
}
