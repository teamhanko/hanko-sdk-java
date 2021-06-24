package io.hanko.sdk.http;

import io.hanko.sdk.config.HankoClientConfig;
import io.hanko.sdk.exception.HankoApiConnectionException;
import io.hanko.sdk.exception.HankoClientException;
import io.hanko.sdk.util.HmacUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Objects;

/**
 * Abstract base client that performs actual requests to the Hanko API.
 */
public abstract class HankoHttpClientBase {
    private final CloseableHttpClient httpClient;
    private final HankoClientConfig config;

    /**
     * Base constructor.
     * @param config a {@link HankoClientConfig}
     * @param httpClient a {@link CloseableHttpClient}
     */
    protected HankoHttpClientBase(HankoClientConfig config, CloseableHttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
        this.config = Objects.requireNonNull(config, "config must not be null");
    }

    /**
     * Performs a request without a request body to the Hanko API.
     *
     * @param method the {@link HttpMethod} for the request
     * @param path   the API endpoint path as a String
     * @return a {@link HankoHttpResponse}
     */
    protected HankoHttpResponse makeRequest(HttpMethod method, String path) {
        return makeRequest(method, path, null);
    }

    /**
     * Performs a request with a request body to the Hanko API.
     *
     * @param method the {@link HttpMethod} for the request
     * @param path   the API endpoint path as a String
     * @param body   nullable, the request body as a String
     * @return a {@link HankoHttpResponse}
     * @throws HankoApiConnectionException - if an error occurs connecting to the Hanko API
     * @throws HankoClientException        - if an error occurs during authorization header construction
     */
    protected HankoHttpResponse makeRequest(HttpMethod method, String path, String body) {
        String authHeader = HmacUtil.makeAuthorizationHeader(
                config.getApiSecret(),
                config.getApiKeyId(),
                method.toString(),
                path,
                body
        );
        String url = constructUrl(path);

        logRequest(method, url, body, authHeader);

        RequestBuilder builder = RequestBuilder.create(method.toString())
                .setUri(url)
                .setHeader("Authorization", authHeader);

        if (body != null) {
            builder.setHeader("Content-Type", "application/json")
                    .setEntity(new StringEntity(body, "UTF-8"));
        }

        HttpResponse response = null;
        try {
            response = httpClient.execute(builder.build());
            HttpEntity entity = response.getEntity();

            return new HankoHttpResponse(
                    entity == null ? null : new BufferedHttpEntity(entity).getContent(),
                    response.getStatusLine().getStatusCode(),
                    path
            );
        } catch (IOException ex) {
            throw new HankoApiConnectionException("Could not connect: ", ex);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }

    }

    private String constructUrl(String path) {
        String baseUrl = config.getApiUrl().toString();
        baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        return baseUrl + path;
    }

    /**
     * Get a logger
     * @return the {@link Logger}
     */
    protected abstract Logger getLogger();

    private void logRequest(HttpMethod method, String url, String body, String authHeader) {
        Logger logger = getLogger();
        logger.debug("-- BEGIN Hanko API Request --");
        logger.debug("request method: {}", method);
        logger.debug("request URL: {}", url);
        logger.debug("authorization: {}", authHeader);
        if (body != null) {
            logger.debug("body: {}", body);
        }
        logger.debug("-- END Hanko API Request --");
    }
}
