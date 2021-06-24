package io.hanko.sdk.config;

import io.hanko.sdk.exception.HankoClientException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HankoClientConfigTest {

    @Test
    void Construct_InvalidApiUrlString_ThrowException() {
        assertThatThrownBy(() -> new HankoClientConfig("invalidUrl", "apiKey"))
            .isInstanceOf(HankoClientException.class)
            .hasMessageContaining("apiUrl must be valid URL");
    }

    @Test
    void Construct_NoApiKey_ThrowException() {
        assertThatThrownBy(() -> new HankoClientConfig("http://localhost", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("apiSecret must not be null");
    }
}