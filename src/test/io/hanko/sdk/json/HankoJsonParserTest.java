package io.hanko.sdk.json;

import io.hanko.sdk.exception.HankoClientException;
import io.hanko.sdk.webauthn.api.WebAuthnCredential;
import io.hanko.sdk.webauthn.protocol.AuthenticatorSelectionCriteria;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HankoJsonParserTest {

    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void Deserialize_InvalidJson_ThrowException() {
        String json = "{ \"userVerification\":\"discouraged\", \"authenticatorAttachment\":\"platform\" ";
        InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        assertThatThrownBy(() -> mapper.deserialize(is, AuthenticatorSelectionCriteria.class))
            .isInstanceOf(HankoClientException.class)
            .hasMessageContaining("Could not deserialize");
    }

    @Test
    void DeserializeList_InvalidJson_ThrowException() throws IOException {
        File file = new File ("src/test/resources/WebAuthnCredentialList_Invalid.json");
        InputStream is = new FileInputStream(file);
        assertThatThrownBy(() -> mapper.deserializeList(is, WebAuthnCredential.class))
                .isInstanceOf(HankoClientException.class)
                .hasMessageContaining("Could not deserialize");
    }
}