package io.hanko.sdk.webauthn.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegistrationInitializationRequestTest {
    @Test
    void Construct_NoUser_ThrowException() {
        assertThatThrownBy(()-> new RegistrationInitializationRequest(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("user must not be null");
    }
}