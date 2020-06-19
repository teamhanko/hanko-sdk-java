package io.hanko.sdk.models;

public class AuthenticatorSelectionCriteria {
    private AuthenticatorAttachment authenticatorAttachment;
    private boolean requireResidentKey;
    private UserVerification userVerification;

    public void setAuthenticatorAttachment(AuthenticatorAttachment authenticatorAttachment) {
        this.authenticatorAttachment = authenticatorAttachment;
    }

    public void setRequireResidentKey(boolean requireResidentKey) {
        this.requireResidentKey = requireResidentKey;
    }

    public void setUserVerification(UserVerification userVerification) {
        this.userVerification = userVerification;
    }

    public AuthenticatorAttachment getAuthenticatorAttachment() {
        return authenticatorAttachment;
    }

    public boolean isRequireResidentKey() {
        return requireResidentKey;
    }

    public UserVerification getUserVerification() {
        return userVerification;
    }
}
