package io.hanko.sdk.webauthn.api;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegistrationInitializationUserTest {

    @Test
    void Construct_NoId_ThrowException() {
        assertThatThrownBy(()-> new RegistrationInitializationUser(null, "name", "displayName"))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("id must not be null");
    }

    @Test
    void Construct_NoName_ThrowException() {
        assertThatThrownBy(()-> new RegistrationInitializationUser("id", null, "displayName"))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("name must not be null");
    }

    @Test
    void Construct_NoDisplayName_ThrowException() {
        assertThatThrownBy(()-> new RegistrationInitializationUser("id", "name", null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("displayName must not be null");
    }

    @Test
    void Construct_ReturnInstance() {
        RegistrationInitializationUser got = new RegistrationInitializationUser("id", "name", "displayName");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(got.getId()).isEqualTo("id");
            softly.assertThat(got.getName()).isEqualTo("name");
            softly.assertThat(got.getDisplayName()).isEqualTo("displayName");
        });
    }
}