package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.webauthn.protocol.AuthenticatorAttachment;
import io.hanko.sdk.webauthn.protocol.UserVerificationRequirement;

/**
 * Allows the setting of additional authenticator attributes for an {@link AuthenticationInitializationRequest}.
 */
public class AuthenticationInitializationOptions {
    private AuthenticatorAttachment authenticatorAttachment;
    private UserVerificationRequirement userVerification;

    /**
     * Construct AuthenticationInitializationOptions.
     */
    public AuthenticationInitializationOptions() {
    }

    /**
     * Construct AuthenticationInitializationOptions.
     * @param authenticatorAttachment the {@link AuthenticatorAttachment} requirement for the authentication operation
     * @param userVerification the {@link UserVerificationRequirement} for the authentication operation
     */
    public AuthenticationInitializationOptions(
            AuthenticatorAttachment authenticatorAttachment,
            UserVerificationRequirement userVerification) {
        this.authenticatorAttachment = authenticatorAttachment;
        this.userVerification = userVerification;
    }

    /**
     * Get the {@link AuthenticatorAttachment}.
     * @return the AuthenticatorAttachment
     */
    public AuthenticatorAttachment getAuthenticatorAttachment() {
        return authenticatorAttachment;
    }

    /**
     * Set the {@link AuthenticatorAttachment}.
     * @param authenticatorAttachment the {@link AuthenticatorAttachment}
     */
    public void setAuthenticatorAttachment(AuthenticatorAttachment authenticatorAttachment) {
        this.authenticatorAttachment = authenticatorAttachment;
    }

    /**
     * Get the {@link UserVerificationRequirement}.
     * @return the {@link UserVerificationRequirement}
     */
    public UserVerificationRequirement getUserVerification() {
        return userVerification;
    }

    /**
     * Set the {@link UserVerificationRequirement}.
     * @param userVerification the {@link UserVerificationRequirement}
     */
    public void setUserVerification(UserVerificationRequirement userVerification) {
        this.userVerification = userVerification;
    }
}
