package io.hanko.sdk.http.apache;

import io.hanko.sdk.HankoClientConfig;
import io.hanko.sdk.http.HankoHttpClient;
import io.hanko.sdk.http.HankoHttpClientFactory;

public class HankoHttpClientFactoryApache implements HankoHttpClientFactory {
    @Override
    public HankoHttpClient create(HankoClientConfig config) {
        return new HankoHttpClientApache(config);
    }
}
