package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.webauthn.protocol.AuthenticatorAttachment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class FinalizationResponseTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void FromJson_ReturnInstance() {
        File json = new File("src/test/resources/FinalizationResponse.json");
        FinalizationResponse got = mapper.deserialize(json, FinalizationResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getCredential().getId()).isEqualTo("r-rmve-IpphRRb2cEZ4Id0pYwzk");
            softly.assertThat(got.getCredential().getCreatedAt()).isEqualTo("2021-06-09T14:05:25.848932Z");
            softly.assertThat(got.getCredential().getLastUsed()).isEqualTo("2021-06-09T14:05:25.848929Z");
            softly.assertThat(got.getCredential().getName()).isEqualTo("Initial Name");
            softly.assertThat(got.getCredential().isUserVerification()).isEqualTo(true);
            softly.assertThat(got.getCredential().isResidentKey()).isEqualTo(false);
            softly.assertThat(got.getCredential().getUser().getId()).isEqualTo("3f10f35e-f6d4-49ce-9db9-840b95e0b7f4");
            softly.assertThat(got.getCredential().getAuthenticator().getAttachment()).isEqualTo(AuthenticatorAttachment.CROSS_PLATFORM);
            softly.assertThat(got.getCredential().getAuthenticator().getAaguid()).isEqualTo("2fc0579f-8113-47ea-b116-bb5a8db9202a");
        });
    }
}