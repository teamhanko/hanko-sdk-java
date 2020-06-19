package io.hanko.sdk.http.apache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import io.hanko.sdk.HankoClientConfig;
import io.hanko.sdk.http.HankoHttpClient;
import io.hanko.sdk.util.HmacUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HankoHttpClientApache implements HankoHttpClient {
    private String baseUrl;
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private final HmacUtil hmacUtil;
    private final HankoClientConfig config;

    public HankoHttpClientApache(HankoClientConfig config) {
        this.baseUrl = config.getApiUrl();
        this.hmacUtil = new HmacUtil();
        this.config = config;
    }

    public InputStream send(String url, String json, HttpEntityEnclosingRequestBase request) {
        request.setHeader("Content-Type", "application/json");

        setupProxy(request);

        // add json body
        try {
            StringEntity entity = new StringEntity(json, "UTF-8");
            request.setEntity(entity);
            String path = request.getURI().getPath();
            String method = request.getMethod();
            String authHeader = hmacUtil.makeAuthorizationHeader(config.getApiKey(), config.getApiKeyId(), method, path, json);
            request.addHeader("Authorization", authHeader);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return executeRequest(request);
    }

    @Override
    public InputStream post(String url, String json) {
        // create request
        HttpPost httpPost = new HttpPost(baseUrl + url);
        return send(url, json, httpPost);
    }

    @Override
    public InputStream put(String url, String json) {
        // create request
        HttpPut httpPut = new HttpPut(baseUrl + url);
        return send(url, json, httpPut);
    }

    @Override
    public InputStream get(String url) {
        // create request
        HttpGet httpGet = new HttpGet(baseUrl + url);

        setupProxy(httpGet);

        try {
            String path = httpGet.getURI().getPath();
            String method = httpGet.getMethod();
            String authHeader = hmacUtil.makeAuthorizationHeader(config.getApiKey(), config.getApiKeyId(), method, path, "");
            httpGet.addHeader("Authorization", authHeader);
            return executeRequest(httpGet);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private InputStream executeRequest(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            HttpEntity resultEntity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 401) {
                String content = new BufferedReader(new InputStreamReader(resultEntity.getContent()))
                        .lines().collect(Collectors.joining("\n"));
                throw new RuntimeException("Hanko API returned 401, please check your API key configuration: " + content);
            }

            if (statusCode >= 300) {
                String content = new BufferedReader(new InputStreamReader(resultEntity.getContent()))
                        .lines().collect(Collectors.joining("\n"));
                throw new RuntimeException("Hanko API returned an unexpected status code: " + statusCode + "\n" + content);
            }

            return resultEntity.getContent();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setupProxy(HttpRequestBase request) {
        if (config.isProxyEnabled()) {
            HttpHost proxy = new HttpHost(config.getProxyAddress(), config.getProxyPort(), config.getProxyType());
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            request.setConfig(config);
        }
    }

    @Override
    public void close() {
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
