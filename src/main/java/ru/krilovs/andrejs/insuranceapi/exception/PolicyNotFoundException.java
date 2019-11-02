package ru.krilovs.andrejs.insuranceapi.exception;

/**
 * TODO: @svisotsky to @akrilovs: Revisit whether this exception truly needs or {@link RuntimeException} is enough
 */
public class PolicyNotFoundException extends RuntimeException {

    public PolicyNotFoundException() {
        super();
    }

    public PolicyNotFoundException(String message) {
        super(message);
    }

    public PolicyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolicyNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PolicyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
