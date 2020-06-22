package io.hanko.sdk.models;

public class CreateWebAuthnRequest extends CommonCreateRequest {
    private AuthenticatorSelectionCriteria authenticatorSelectionCriteria;
    private UserVerification userVerificationRequirement;
    private String displayName;

    public void setAuthenticatorSelectionCriteria(AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
        this.authenticatorSelectionCriteria = authenticatorSelectionCriteria;
    }

    public void setUserVerificationRequirement(io.hanko.sdk.models.UserVerification userVerificationRequirement) {
        this.userVerificationRequirement = userVerificationRequirement;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelectionCriteria() {
        return authenticatorSelectionCriteria;
    }

    public UserVerification getUserVerificationRequirement() {
        return userVerificationRequirement;
    }

    public String getDisplayName() {
        return displayName;
    }
}
