package com.cf.storage.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
 * <p>类名称     ：com.fst.common.FstLogger</p>
 * <p>描述          ：</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月11日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
@SuppressWarnings("rawtypes")
public class FstLogger {

    private Logger log;

    private FstLogger() {
        log = LogManager.getLogger();
    }

    private FstLogger( Class c) {
        log = LogManager.getLogger();
    }

    private FstLogger(String className) {
        log = LogManager.getLogger(className);
    }

    public static FstLogger getLogger() {
        return new FstLogger();
    }

    public static FstLogger getLogger(Class c) {

        return new FstLogger(c);
    }

    public static FstLogger getLogger(String className) {
        return new FstLogger(className);
    }

    public void trace(String info) {
            log.trace(info);
    }

    public void debug(String info) {
            log.debug(info);
    }

    public void info(String info) {
            log.info(info);
    }
    
    public void info(String info,Object p0,Object p1) {
        log.info(info, p0, p1);
    }

    public void warn(String info) {
            log.warn(info);
    }

    public void error(String info) {
            log.error(info);
    }

    public void error(Object info, Throwable t) {
            log.error(info + "," + t);
    }

    public void fatal(String info) {
            log.fatal(info);
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    public boolean isFatalEnabled() {
        return log.isFatalEnabled();
    }
}