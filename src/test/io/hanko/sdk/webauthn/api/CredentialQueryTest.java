package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.json.HankoJsonParser;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CredentialQueryTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @Test
    void ToQueryString_DefaultConstructor_ReturnEmptyString() {
        CredentialQuery query = new CredentialQuery();
        String got = query.toQueryString();
        assertThat(got).isEmpty();
    }

    @Test
    void ToQueryString_SetValues_ReturnStringContainingOnlySetValues() {
        CredentialQuery query = new CredentialQuery();
        query.setUserid("userId");
        String got = query.toQueryString();
        assertThat(got).isEqualTo("?user_id=userId");
    }

    @Test
    void FromJson_ReturnInstance() {
        String json = "{\"user_id\": \"userId\", \"page_size\": 5, \"page\": 6}";
        CredentialQuery got = mapper.deserialize(json, CredentialQuery.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getUserid()).isEqualTo("userId");
            softly.assertThat(got.getPageSize()).isEqualTo(5);
            softly.assertThat(got.getPage()).isEqualTo(6);
        });
    }
}