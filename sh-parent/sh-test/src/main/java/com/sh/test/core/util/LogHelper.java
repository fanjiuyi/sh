package com.sh.test.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 范玖祎
 * @date 2016/12/6 10:47
 * @description 日志打印工具
 */
public class LogHelper {
    private final static Logger log = LoggerFactory.getLogger(LogHelper.class);

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String message,Object ... objects) {
        log.info(message, objects);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message ,Object ... objects) {
        log.error(message ,objects);
    }

    public static void error(String message ,Throwable throwable ) {
        log.error(message , throwable);
    }


}

