package ru.krilovs.andrejs.insuranceapi.exception;

/**
 * TODO: @svisotsky to @akrilovs: Revisit whether this exception truly needs or {@link RuntimeException} is enough
 */
public class InvalidPolicyDataException extends RuntimeException {

    public InvalidPolicyDataException() {
        super();
    }

    public InvalidPolicyDataException(String message) {
        super(message);
    }

    public InvalidPolicyDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPolicyDataException(Throwable cause) {
        super(cause);
    }

    protected InvalidPolicyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
