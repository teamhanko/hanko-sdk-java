package io.hanko.sdk.webauthn.protocol;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A WebAuthn Relying Party may require user verification for some of its operations but not for
 * others, and may use this type to express its needs.
 *
 * @see <a href="https://www.w3.org/TR/webauthn-1/#enumdef-userverificationrequirement">
 * Web Authentication Level 1 - 5.10.6. User Verification Requirement Enumeration</a>
 */
public enum UserVerificationRequirement {
    /**
     * This value indicates that the Relying Party requires user verification for the operation and
     * will fail the operation if the response does not have the UV flag set.
     */
    REQUIRED("required"),

    /**
     * This value indicates that the Relying Party prefers user verification for the operation
     * if possible, but will not fail the operation if the response does not have the UV flag set.
     */
    PREFERRED("preferred"),

    /**
     * This value indicates that the Relying Party does not want user verification employed during
     * the operation (e.g., in the interest of minimizing disruption to the user interaction flow).
     */
    DISCOURAGED("discouraged");

    String userVerification;

    UserVerificationRequirement(String value) {
        this.userVerification = value;
    }

    /**
     * Construct a UserVerificationRequirement from a String value.
     *
     * Used for JSON deserialization.
     * @param value the String value
     * @return the UserVerificationRequirement
     */
    @JsonCreator
    public static UserVerificationRequirement fromValue(String value) {
        switch (value) {
            case "required":
                return REQUIRED;
            case "preferred":
                return PREFERRED;
            case "discouraged":
                return DISCOURAGED;
            default:
                return null;
        }
    }

    /**
     * Return the {@link #userVerification} value of the constant.
     * @return the {@link #userVerification} value
     */
    @JsonValue
    public String getUserVerification() {
        return userVerification;
    }
}
