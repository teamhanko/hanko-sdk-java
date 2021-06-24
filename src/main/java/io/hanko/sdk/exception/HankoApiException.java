package io.hanko.sdk.exception;

/**
 * Exception occurring when the Hanko API does not respond with a status code in the range 2xx.
 */
public class HankoApiException extends HankoException {
    /**
     * The Hanko API response content.
     */
    private final String content;
    /**
     * The Hanko API response HTTP status.
     */
    private final Integer status;
    /**
     * The original request path.
     */
    private final String requestPath;

    /**
     * Construct a new HankoApiException with the specified detail message.
     * @param message the detail message
     */
    public HankoApiException(String message) {
        this(message, null, null, null);
    }

    /**
     * Construct a new HankoApiException with the specified detail message, the
     * Hanko API response status, the original request path and the Hanko API response content.
     * @param message the detail message
     * @param status the Hanko API HTTP response status
     * @param requestPath the original request path
     * @param content the Hanko API response content
     */
    public HankoApiException(String message, Integer status, String requestPath, String content) {
        super(message);
        this.content = content;
        this.status = status;
        this.requestPath = requestPath;
    }

    /**
     * Get the Hanko API response content.
     * @return the Hanko API response content as a String
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the Hanko API HTTP response status.
     * @return the Hanko API HTTP response status as an Integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Get the original request path.
     * @return the original request path as a String
     */
    public String getRequestPath() {
        return requestPath;
    }
}
