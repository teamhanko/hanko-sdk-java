package io.hanko.sdk.webauthn.protocol;

import io.hanko.sdk.json.HankoJsonParser;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AuthenticatorSelectionCriteriaTest {

    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void FromJson_ReturnInstance() throws IOException {
        String json = "{ \"userVerification\":\"discouraged\", \"authenticatorAttachment\":\"platform\", \"requireResidentKey\":\"true\" }";
        AuthenticatorSelectionCriteria got = mapper.deserialize(json, AuthenticatorSelectionCriteria.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getUserVerification()).isEqualTo(UserVerificationRequirement.DISCOURAGED);
            softly.assertThat(got.getAuthenticatorAttachment()).isEqualTo(AuthenticatorAttachment.PLATFORM);
            softly.assertThat(got.isRequireResidentKey()).isTrue();
        });
    }
}
