package org.drools.examples.taobao.util;

/**
 * Created by xiyan on 2016/11/13.
 */
public class LoggerUtils {
    public static void warn(String msg) {
        System.out.println(msg);
    }
    public static void exception(String msg, Throwable throwable) {
        System.out.println(msg);
    }

}
