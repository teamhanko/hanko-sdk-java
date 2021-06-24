package io.hanko.sdk.http;

import io.hanko.sdk.config.HankoClientConfig;
import io.hanko.sdk.exception.HankoApiException;
import io.hanko.sdk.exception.HankoAuthenticationException;
import io.hanko.sdk.exception.HankoNotFoundException;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.hanko.sdk.http.HttpMethod.*;

/**
 * Main {@link HankoHttpClientBase} extension providing methods aligned with HTTP verbs/operations
 * used with the Hanko API. Handles API error responses.
 */
public class HankoHttpClient extends HankoHttpClientBase {

    /**
     * Construct a HankoHttpClient.
     *
     * Uses a default underlying {@link CloseableHttpClient}.
     * @param config non-null, the {@link HankoClientConfig}
     */
    public HankoHttpClient(HankoClientConfig config) {
        this(config, HttpClients.createDefault());
    }

    /**
     * Construct a HankoHttpClient using a custom underlying {@link CloseableHttpClient}.
     *
     * @param config the {@link HankoClientConfig}
     * @param httpClient non-null, the {@link CloseableHttpClient}
     */
    public HankoHttpClient(HankoClientConfig config, CloseableHttpClient httpClient) {
        super(config, httpClient);
    }

    /**
     * Make a POST request to the Hanko API.
     * @param path the API endpoint path as a String
     * @param body the request body as a String
     * @return a {@link HankoHttpResponse}
     * @throws HankoApiException if the {@link HankoHttpResponse} does not contain a 2xx response code
     */
    public HankoHttpResponse post(String path, String body) {
        HankoHttpResponse response = makeRequest(POST, path, body);
        if (!response.success()) {
            handleErrorResponse(response);
        }
        return response;
    }

    /**
     * Make a GET request to the Hanko API.
     * @param path the API endpoint path as a String
     * @return a {@link HankoHttpResponse}
     * @throws HankoApiException if the {@link HankoHttpResponse} does not contain a 2xx response code
     */
    public HankoHttpResponse get(String path) {
        HankoHttpResponse response = makeRequest(GET, path);
        if (!response.success()) {
            handleErrorResponse(response);
        }
        return response;
    }

    /**
     * Make a PUT request to the Hanko API.
     * @param path the API endpoint path as a String
     * @param body the request body as a String
     * @return a {@link HankoHttpResponse}
     * @throws HankoApiException if the {@link HankoHttpResponse} does not contain a 2xx response code
     */
    public HankoHttpResponse put(String path, String body) {
        HankoHttpResponse response = makeRequest(PUT, path, body);
        if (!response.success()) {
            handleErrorResponse(response);
        }
        return response;
    }

    /**
     * Make a delete request to the Hanko API.
     * @param path the API endpoint path as a String
     * @return a {@link HankoHttpResponse}
     * @throws HankoApiException if the {@link HankoHttpResponse} does not contain a 2xx response code
     */
    public HankoHttpResponse delete(String path) {
        HankoHttpResponse response = makeRequest(DELETE, path);
        if (!response.success()) {
            handleErrorResponse(response);
        }
        return response;
    }

    private void handleErrorResponse(HankoHttpResponse response) {
        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
            throw new HankoAuthenticationException(
                    "Hanko API returned 401, please check your API key configuration",
                    response.getStatusCode(), response.getRequestPath(), response.getContent()
            );
        }

        if (response.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            String message = String.format("Hanko API resource '%s' not found.", response.getRequestPath());
            throw new HankoNotFoundException(
                    message,
                    response.getStatusCode(), response.getContent(), response.getRequestPath()
            );
        }

        throw new HankoApiException(
                "Hanko API returned an unexpected status code",
                response.getStatusCode(), response.getRequestPath(), response.getContent()
        );
    }

    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(HankoHttpClient.class);
    }
}
