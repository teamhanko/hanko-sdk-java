package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.webauthn.protocol.AuthenticatorAttachment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class WebAuthnCredentialTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void FromJson_ReturnInstance() {
        File json = new File("src/test/resources/WebAuthnCredential.json");
        WebAuthnCredential got = mapper.deserialize(json, WebAuthnCredential.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getId()).isEqualTo("r-rmve-IpphRRb2cEZ4Id0pYwzk");
            softly.assertThat(got.getCreatedAt()).isEqualTo("2021-06-09T14:05:25.848932Z");
            softly.assertThat(got.getLastUsed()).isEqualTo("2021-06-09T14:05:25.848929Z");
            softly.assertThat(got.getName()).isEqualTo("My security key");
            softly.assertThat(got.isUserVerification()).isEqualTo(true);
            softly.assertThat(got.isResidentKey()).isEqualTo(false);
            softly.assertThat(got.getUser().getId()).isEqualTo("3f10f35e-f6d4-49ce-9db9-840b95e0b7f4");
            softly.assertThat(got.getAuthenticator().getAttachment()).isEqualTo(AuthenticatorAttachment.CROSS_PLATFORM);
            softly.assertThat(got.getAuthenticator().getAaguid()).isEqualTo("e6344cda-ed2a-493f-b023-b9986baf2296");
        });
    }
}