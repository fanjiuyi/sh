package com.sh.utils.logger;

/**
 * Created by Administrator on 2016/9/18.
 */

import com.sh.utils.common.StringUtils;
import org.apache.log4j.Logger;

public class LogHelper {

    public static void info(String message) {
        info(null, message);
    }

    public static void info(Class target, String message) {
        Logger log;
        if (null == target) {
            log = Logger.getRootLogger();
        } else {
            log = Logger.getLogger(target.getClass());
        }
        if (StringUtils.isNotEmpty((message))) {
            log.info(message);
        }
    }

    public static void debug(String message) {
        debug(null, message);
    }

    public static void debug(Object target, String message) {
        Logger log;
        if (null == target) {
            log = Logger.getRootLogger();
        } else {
            log = Logger.getLogger(target.getClass());
        }
        if (StringUtils.isNotEmpty((message))) {
            log.debug(message);
        }
    }

    public static void warn(String message) {
        warn(null, message);
    }

    public static void warn(Object target, String message) {
        Logger log;
        if (null == target) {
            log = Logger.getRootLogger();
        } else {
            log = Logger.getLogger(target.getClass());
        }
        if (StringUtils.isNotEmpty((message))) {
            log.warn(message);
        }
    }

    public static void error(String message, Throwable t) {
        Logger log = Logger.getRootLogger();

        if (StringUtils.isNotEmpty((message))) {
            log.error(message, t);
        }
    }

    public static void error(String message) {
        error(null, message);
    }

    public static void error(Object target, String message) {
        Logger log;
        if (null == target) {
            log = Logger.getRootLogger();
        } else {
            log = Logger.getLogger(target.getClass());
        }

        if (message != null) {
            log.error(message);
        }
    }
}



