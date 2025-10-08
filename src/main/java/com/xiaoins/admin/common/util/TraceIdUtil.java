package com.xiaoins.admin.common.util;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;

/**
 * 追踪ID工具类
 */
public class TraceIdUtil {

    private static final String TRACE_ID = "traceId";

    /**
     * 生成并设置TraceId
     */
    public static String generateAndSet() {
        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(TRACE_ID, traceId);
        return traceId;
    }

    /**
     * 获取TraceId
     */
    public static String get() {
        return MDC.get(TRACE_ID);
    }

    /**
     * 设置TraceId
     */
    public static void set(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 清除TraceId
     */
    public static void clear() {
        MDC.remove(TRACE_ID);
    }
}

