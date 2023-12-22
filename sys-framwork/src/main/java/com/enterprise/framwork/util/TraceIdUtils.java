package com.enterprise.framwork.util;

import java.util.UUID;

public class TraceIdUtils {
    public static final String X_TRACE_ID = "traceId";
    /*** 通过 ThreadLocal 存储 traceId，保证同一个线程，可以获取到同一个 traceId*/
    private static final ThreadLocal<String> TRACE_ID_LOCAL = new ThreadLocal();

    private TraceIdUtils() {}

    public static String genTraceId() {
        String traceId = genTraceIdNotCached();
        TRACE_ID_LOCAL.set(traceId);
        return traceId;
    }

    public static String genTraceIdNotCached() {
        return String.valueOf(UUID.randomUUID().toString().split("-")[4]);
    }

    public static String getTraceId() {
        return TRACE_ID_LOCAL.get();
    }

    public static void clear() {
        TRACE_ID_LOCAL.remove();
    }
}
