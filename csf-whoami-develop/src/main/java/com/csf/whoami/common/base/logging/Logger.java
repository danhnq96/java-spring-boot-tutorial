package com.csf.whoami.common.base.logging;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class Logger {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    Marker info = MarkerFactory.getMarker("INFO");
    Marker warn = MarkerFactory.getMarker("WANR");
    Marker error = MarkerFactory.getMarker("ERROR");
    Marker debug = MarkerFactory.getMarker("DEBUG");
    Marker fatal = MarkerFactory.getMarker("FATAL");

    public Logger() {
        super();
        setLogger(logger);
    }

    public Logger(Class<?> cls) {
        super();
        setLogger(LoggerFactory.getLogger(cls));
    }

    public Logger(String name) {
        super();
        setLogger(LoggerFactory.getLogger(name));
    }

    public static void destroy() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        rootLogger.detachAndStopAllAppenders();
    }

    public void debug(Object obj) {
        getLogger().debug(debug, "[DEBUG]" + obj);
    }

    public void debug(Object obj, Throwable t) {
        getLogger().debug(debug, "[DEBUG]" + obj, t);
    }

    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    public void info(Object obj) {
        getLogger().info(info, "[INFO ]" + obj);
    }

    /**
     * @param obj
     * @param t
     */
    public void info(Object obj, Throwable t) {
        getLogger().info(info, "[INFO ]" + obj, t);
    }

    /**
     * @return
     */
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * @param obj
     */
    public void warn(Object obj) {
        getLogger().warn(warn, "[WARN ]" + obj);
    }

    /**
     * @param obj
     * @param t
     */
    public void warn(Object obj, Throwable t) {
        getLogger().warn(warn, "[WARN ]" + obj, t);
    }

    /**
     * @param obj
     */
    public void fatal(Object obj) {
        getLogger().error(fatal, "[FATAL]" + obj);
    }

    /**
     * @param obj
     * @param t
     */
    public void fatal(Object obj, Throwable t) {
        getLogger().error(fatal, "[FATAL]" + obj, t);
    }

    /**
     * @param obj
     */
    public void error(Object obj) {
        getLogger().error(error, "[ERROR]" + obj);
    }

    /**
     * @param obj
     * @param t
     */
    public void error(Object obj, Throwable t) {
        getLogger().error(error, "[ERROR]" + obj, t);
    }

    /**
     * @param obj
     */
    public void start(Object obj) {
        getLogger().info(info, "[START]" + obj);
    }

    /**
     * @param obj
     */
    public void end(Object obj) {
        getLogger().info(info, "[END  ]" + obj);
    }

    /**
     * �f�t�H���g��Priority��INFO�ɂ���.
     *
     * @see Logger#log(Object)
     * @see Logger#log(Object, Throwable)
     */
    public void setInfo() {
        this.defaultPriority = Level.INFO;
    }

    /**
     * �f�t�H���g��Priority��WARN�ɂ���.
     *
     * @see Logger#log(Object)
     * @see Logger#log(Object, Throwable)
     */
    public void setWarn() {
        this.defaultPriority = Level.WARN;
    }

    /**
     * �f�t�H���g��Priority��ERROR�ɂ���.
     *
     * @see Logger#log(Object)
     * @see Logger#log(Object, Throwable)
     */
    public void setError() {
        this.defaultPriority = Level.ERROR;
    }

    /**
     * �f�t�H���g��Priority��DEBUG�ɂ���.
     *
     * @see Logger#log(Object)
     * @see Logger#log(Object, Throwable)
     */
    public void setDebug() {
        this.defaultPriority = Level.DEBUG;
    }

    /**
     * �f�t�H���g��Priority��FATAL�ɂ���.
     *
     * @see Logger#log(Object)
     * @see Logger#log(Object, Throwable)
     */
    public void setFatal() {
        this.defaultPriority = Level.ERROR;
    }

    /**
     * �f�t�H���g��Priotiry�Ń��O������.
     *
     * @param obj
     */
    public void log(Object obj) {
        getLogger().trace(defaultPriority.toString(), obj);
    }

    /**
     * �f�t�H���g��Priotiry�Ń��O������.
     *
     * @param obj
     * @param t
     */
    public void log(Object obj, Throwable t) {
        getLogger().trace(defaultPriority.toString(), obj, t);
    }

    private Level defaultPriority;

    /**
     * Returns the logger.
     *
     * @return Log
     */
    public org.slf4j.Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger.
     *
     * @param logger The logger to set
     */
    public void setLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

}
