package io.hanko.sdk.webauthn.api;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TransactionInitializationRequestTest {

    @Test
    void Construct_NoTransaction_ThrowException() {
        assertThatThrownBy(() -> new TransactionInitializationRequest(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("transactionText must not be null");
    }

    @Test
    void Construct_ReturnInstance() {
        TransactionInitializationRequest got = new TransactionInitializationRequest("transactionText");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getUser()).isNull();
            softly.assertThat(got.getOptions()).isNull();
            softly.assertThat(got.getTransaction()).isEqualTo("transactionText");
        });
    }
}