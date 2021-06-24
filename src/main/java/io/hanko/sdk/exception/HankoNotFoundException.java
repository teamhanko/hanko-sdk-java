package io.hanko.sdk.exception;

/**
 * Exception occurring if requested resources are not found by the Hanko API (i.e. if it
 * response with a 404 status code).
 */
public class HankoNotFoundException extends HankoApiException {

    /**
     * Construct a new HankoNotFoundException with the specified detail message.
     * @param message the detail message
     */
    public HankoNotFoundException(String message) {
        super(message);
    }

    /**
     * Construct a new HankoNotFoundException with the specified detail message, the
     * Hanko API response status, the original request path and the Hanko API response content.
     * @param message the detail message
     * @param status the Hanko API HTTP response status
     * @param requestPath the original request path
     * @param content the Hanko API response content
     */
    public HankoNotFoundException(String message, Integer status, String requestPath, String content) {
        super(message, status, requestPath, content);
    }
}
