package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.json.HankoJsonParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CredentialUpdateRequestTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void FromJson_ReturnInstance() {
        String json = "{\"name\": \"newName\"}";
        CredentialUpdateRequest got = mapper.deserialize(json, CredentialUpdateRequest.class);
        assertThat(got.getName()).isEqualTo("newName");
    }

}