package com.lei.util;

public class Constant {
    public final static String DISTRIBUTED_TRACE_ID = "ley-trace-id";
    public final static String DEFAULT_POINT_CUT_EXPRESSION = "execution(* com..*.*Controller.*(..))";
    public final static String DISABLED_POINT_CUT_EXPRESSION = "execution(* DISABLE..*.*(..))";
}
