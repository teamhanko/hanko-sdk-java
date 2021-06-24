package io.hanko.sdk.webauthn.protocol;

import io.hanko.sdk.json.HankoJsonParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AttestationConveyancePreferenceTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @ParameterizedTest
    @MethodSource("provideStringsForReturnInstance")
    void FromJson_ReturnInstance(String input, AttestationConveyancePreference expected) {
        assertThat(mapper.deserialize(input, AttestationConveyancePreference.class)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForReturnInstance() {
        return Stream.of(
                Arguments.of("\"direct\"", AttestationConveyancePreference.DIRECT),
                Arguments.of("\"indirect\"", AttestationConveyancePreference.INDIRECT),
                Arguments.of("\"none\"", AttestationConveyancePreference.NONE),
                Arguments.of("\"null\"", null),
                Arguments.of("\"\"", null)
        );
    }
}