package io.hanko.sdk.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HmacUtilTest {
    @Test
    void MakeAuthorizationHeader_WithApiKeyId_ReturnHmacHeader() {
        String got = HmacUtil.makeAuthorizationHeader("apiKey", "apiKeyId", "POST", "/my/path", "content");
        assertThat(got.split(" ")[0]).isEqualTo("hanko");
    }

    @Test
    void MakeAuthorizationHeader_NoApiKeyId_ReturnSecretHeader() {
        String got = HmacUtil.makeAuthorizationHeader("apiKey", null, "POST", "/my/path", "content");
        assertThat(got.split(" ")[0]).isEqualTo("secret");
    }
}