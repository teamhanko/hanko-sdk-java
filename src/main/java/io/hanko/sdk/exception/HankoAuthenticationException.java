package io.hanko.sdk.exception;

/**
 * Exception occurring when requests to the Hanko API cannot be authenticated (i.e. have a 401
 * status code).
 */
public class HankoAuthenticationException extends HankoApiException {
    /**
     * Construct a new HankoAuthenticationException with the specified detail message.
     * @param message the detail message
     */
    public HankoAuthenticationException(String message) {
        this(message, null, null, null);
    }

    /**
     * Construct a new HankoAuthenticationException with the specified detail message, the
     * Hanko API response status, the original request path and the Hanko API response content.
     * @param message the detail message
     * @param status the Hanko API HTTP response status
     * @param requestPath the original request path
     * @param content the Hanko API response content
     */
    public HankoAuthenticationException(String message, Integer status, String requestPath, String content) {
        super(message, status, requestPath, content);
    }
}
