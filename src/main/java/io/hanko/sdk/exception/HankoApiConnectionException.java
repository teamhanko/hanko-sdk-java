package io.hanko.sdk.exception;

/**
 * Exception occurring when no proper connection to the Hanko API can be established.
 */
public class HankoApiConnectionException extends HankoException {
    /**
     * Construct a new HankoApiConnectionException with the specified detail message and
     * cause.
     * @param message the detail message
     * @param cause the cause
     */
    public HankoApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
