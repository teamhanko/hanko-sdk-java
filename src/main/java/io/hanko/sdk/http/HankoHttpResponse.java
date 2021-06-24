package io.hanko.sdk.http;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Encapsulates a Hanko API response.
 */
public class HankoHttpResponse {
    private final InputStream stream;
    private final int statusCode;
    private final String requestPath;

    /**
     * Create a HankoHttpResponse.
     *
     * @param stream     input stream
     * @param statusCode status code
     * @param requestPath the original request path
     */
    public HankoHttpResponse(InputStream stream,int statusCode, String requestPath) {
        this.stream = stream;
        this.statusCode = statusCode;
        this.requestPath = requestPath;
    }

    /**
     * Get the response content as an InputStream.
     * @return the response content as an InputStream
     */
    public InputStream getStream() {
        return stream;
    }

    /**
     * Get the response content as String.
     * @return The response content as a String
     */
    public String getContent() {
        if (stream != null) {
            Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");

            if (!scanner.hasNext()) {
                return "";
            }

            String content = scanner.next();
            scanner.close();

            return content;
        }
        return "";
    }

    /**
     * Get the HTTP status code of the response.
     * @return the HTTP status code as an int
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the original request path for the related request.
     * @return the request path as a String
     */
    public String getRequestPath() {
        return requestPath;
    }

    /**
     * Check whether the response indicates a successful request,
     * i.e.: whether the response status code is in the 2xx range.
     * @return true if the request was successful, false otherwise
     */
    public boolean success() {
        return statusCode >= 200 && statusCode < 300;
    }

}