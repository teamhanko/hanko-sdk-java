package io.hanko.sdk.webauthn.protocol;

import io.hanko.sdk.json.HankoJsonParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticatorAttachmentTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @ParameterizedTest
    @MethodSource("provideStringsForReturnInstance")
    void FromJson_ReturnInstance(String input, AuthenticatorAttachment expected) {
        assertThat(mapper.deserialize(input, AuthenticatorAttachment.class)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForReturnInstance() {
        return Stream.of(
                Arguments.of("\"platform\"", AuthenticatorAttachment.PLATFORM),
                Arguments.of("\"cross-platform\"", AuthenticatorAttachment.CROSS_PLATFORM),
                Arguments.of("\"null\"", null),
                Arguments.of("\"\"", null)
        );
    }

}