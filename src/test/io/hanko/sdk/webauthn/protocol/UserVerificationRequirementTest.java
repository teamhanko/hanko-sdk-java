package io.hanko.sdk.webauthn.protocol;

import io.hanko.sdk.json.HankoJsonParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UserVerificationRequirementTest {
    HankoJsonParser mapper = new HankoJsonParser();

    @ParameterizedTest
    @MethodSource("provideStringsForReturnInstance")
    void FromJson_ReturnInstance(String input, UserVerificationRequirement expected) {
        assertThat(mapper.deserialize(input, UserVerificationRequirement.class)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForReturnInstance() {
        return Stream.of(
                Arguments.of("\"discouraged\"", UserVerificationRequirement.DISCOURAGED),
                Arguments.of("\"preferred\"", UserVerificationRequirement.PREFERRED),
                Arguments.of("\"required\"", UserVerificationRequirement.REQUIRED),
                Arguments.of("\"null\"", null),
                Arguments.of("\"\"", null)
        );
    }
}
