package com.csf.whoami.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    public static <T> void logError(T thisClass, String message) {
        Logger log = LoggerFactory.getLogger(thisClass.getClass());
        log.error(thisClass.getClass().toString() + " - Message : " + message);
    }

    public static <T> void log(T thisClass, String content) {
        Logger log = LoggerFactory.getLogger(thisClass.getClass());
        log.info("INFO : " + content);
    }
}
