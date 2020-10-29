package io.hanko.sdk.http;

import java.io.InputStream;

public interface HankoHttpClient {
    InputStream post(String url, String json);
    InputStream put(String url, String json);
    InputStream get(String url);
    InputStream delete(String url);
    void close();
}
