package io.hanko.sdk.exception;

/**
 * Exception occurring on errors during serialization of request payloads or deserialization
 * of Hanko API response payloads or HMAC calculation.
 */
public class HankoClientException extends HankoException{
    /**
     * Construct a new HankoClientException with the specified detail message and
     * cause.
     * @param message the detail message
     * @param cause the cause
     */
    public HankoClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
