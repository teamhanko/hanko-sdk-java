package io.hanko.sdk.util;

import io.hanko.sdk.HankoClient;
import io.hanko.sdk.HankoClientConfig;
import io.hanko.sdk.http.HankoHttpClientFactory;
import io.hanko.sdk.http.apache.HankoHttpClientFactoryApache;
import io.hanko.sdk.json.HankoJsonParserFactory;
import io.hanko.sdk.json.jackson.HankoJsonParserFactoryJackson;

import java.util.Formatter;

public class HankoUtils {
    public static String asHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static HankoClient createHankoClient(HankoClientConfig hankoClientConfig) {
        HankoHttpClientFactory httpClientFactory =
                new HankoHttpClientFactoryApache();
        HankoJsonParserFactory jsonParserFactory = new HankoJsonParserFactoryJackson();
        return new HankoClient(httpClientFactory, jsonParserFactory, hankoClientConfig);
    }
}
