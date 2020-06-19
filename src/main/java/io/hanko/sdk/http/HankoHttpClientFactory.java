package io.hanko.sdk.http;

import io.hanko.sdk.HankoClientConfig;

public interface HankoHttpClientFactory {
    public HankoHttpClient create(HankoClientConfig config);
}
