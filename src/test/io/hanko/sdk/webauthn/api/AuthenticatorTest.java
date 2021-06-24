package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.webauthn.protocol.AuthenticatorAttachment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class AuthenticatorTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void FromJson_ReturnInstance() {
        String json = "{\"aaguid\": \"2fc0579f-8113-47ea-b116-bb5a8db9202a\",\"attachment\":\"cross-platform\",\"name\":\"YubiKey 5 NFC\"}";
        Authenticator got = mapper.deserialize(json, Authenticator.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getAaguid()).isEqualTo("2fc0579f-8113-47ea-b116-bb5a8db9202a");
            softly.assertThat(got.getName()).isEqualTo("YubiKey 5 NFC");
            softly.assertThat(got.getAttachment()).isEqualTo(AuthenticatorAttachment.CROSS_PLATFORM);
        });
    }
}