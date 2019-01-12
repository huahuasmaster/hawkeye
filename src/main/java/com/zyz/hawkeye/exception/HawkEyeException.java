package com.zyz.hawkeye.exception;

public class HawkEyeException extends RuntimeException {
    public HawkEyeException(String msg) {
        super(msg);
    }

    public HawkEyeException(String msg, Throwable t) {
        super(msg, t);
    }
}
