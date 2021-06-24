package io.hanko.sdk.exception;

/**
 * Base Exception for all Hanko exceptions.
 */
public abstract class HankoException extends RuntimeException {

    /**
     * Construct a new HankoException with the specified detail message.
     * @param message the detail message
     */
    protected HankoException(final String message) {
        this(message, null);
    }

    /**
     * Construct a new HankoException with the specified detail message and
     * cause.
     * @param message the detail message
     * @param cause the cause
     */
    protected HankoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
