package ru.krilovs.andrejs.insuranceapi.exception;

/**
 * TODO: @svisotsky to @akrilovs: Revisit whether this exception truly needs or {@link RuntimeException} is enough
 */
public class InvalidPolicyStructureException extends RuntimeException {

    public InvalidPolicyStructureException() {
        super();
    }

    public InvalidPolicyStructureException(String message) {
        super(message);
    }

    public InvalidPolicyStructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPolicyStructureException(Throwable cause) {
        super(cause);
    }

    protected InvalidPolicyStructureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
