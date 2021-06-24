package io.hanko.sdk.webauthn.protocol;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enumeration's values describe authenticators' attachment modalities. Relying Parties use this for two purposes:
 * <ul>
 * <li>to express a preferred authenticator attachment modality when calling navigator.credentials.create()
 * to create a credential, and</li>
 * <li>to inform the client of the Relying Party's best belief about how to locate the managing authenticators of
 * the credentials listed in allowCredentials when calling navigator.credentials.get().</li>
 * </ul>
 *
 * @see <a href="https://www.w3.org/TR/webauthn-1/#attachment">
 * Web Authentication Level 1 - 5.4.5. Authenticator Attachment Enumeration</a>
 */
public enum AuthenticatorAttachment {
    /**
     * Indicates platform attachment.
     */
    PLATFORM("platform"),
    /**
     * Indicates cross-platform attachment.
     */
    CROSS_PLATFORM("cross-platform");

    private final String attachment;

    private AuthenticatorAttachment(String authenticatorAttachment) {
        this.attachment = authenticatorAttachment;
    }

    /**
     * Construct an AuthenticatorAttachment from a String value.
     *
     * Used for JSON deserialization.
     * @param value the String value
     * @return the AuthenticatorAttachment
     */
    @JsonCreator
    public static AuthenticatorAttachment fromValue(String value) {
        switch (value) {
            case "cross-platform":
                return CROSS_PLATFORM;
            case "platform":
                return PLATFORM;
            default:
                return null;
        }
    }

    /**
     * Return the {@link #attachment} of the constant.
     * @return the {@link #attachment} value
     */
    @JsonValue
    public String getAttachment() {
        return attachment;
    }
}
