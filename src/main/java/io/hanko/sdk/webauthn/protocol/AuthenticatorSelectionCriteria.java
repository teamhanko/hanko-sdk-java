package io.hanko.sdk.webauthn.protocol;

/**
 * WebAuthn Relying Parties may use the AuthenticatorSelectionCriteria to specify their
 * requirements regarding authenticator attributes.
 *
 * @see <a href="https://www.w3.org/TR/webauthn-1/#dictdef-authenticatorselectioncriteria">
 * Web Authentication Level 1 - 5.4.4. Authenticator Selection Criteria</a>
 */
public class AuthenticatorSelectionCriteria {
    private AuthenticatorAttachment authenticatorAttachment;
    private boolean requireResidentKey;
    private UserVerificationRequirement userVerification;

    /**
     * Construct AuthenticatorSelectionCriteria.
     */
    public AuthenticatorSelectionCriteria() {
    }

    /**
     * Construct AuthenticatorSelectionCriteria.
     * @param authenticatorAttachment nullable, the {@link AuthenticatorAttachment}
     * @param requireResidentKey whether a credential should be registered as a resident key,
     *                           defaults to {@code false}
     * @param userVerification nullable, the {@link UserVerificationRequirement}
     */
    public AuthenticatorSelectionCriteria(
            AuthenticatorAttachment authenticatorAttachment,
            boolean requireResidentKey,
            UserVerificationRequirement userVerification) {
        this.authenticatorAttachment = authenticatorAttachment;
        this.requireResidentKey = requireResidentKey;
        this.userVerification = userVerification;
    }

    /**
     * Get the {@link AuthenticatorAttachment}.
     * @return the {@link AuthenticatorAttachment}
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
     * Get the resident key requirement.
     * @return {@code true}, if a resident key should be created using these selection criteria, {@code false} otherwise
     */
    public boolean isRequireResidentKey() {
        return requireResidentKey;
    }

    /**
     * Get the resident key requirement.
     * @param requireResidentKey the resident key requirement
     *                           set to {@code true}, if a resident key should be created using these selection criteria,
     *                           {@code false} otherwise
     */
    public void setRequireResidentKey(boolean requireResidentKey) {
        this.requireResidentKey = requireResidentKey;
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
