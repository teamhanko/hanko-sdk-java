package io.hanko.sdk.models;

public class CreateWebAuthnRequest extends CommonCreateRequest {
    private AuthenticatorSelectionCriteria authenticatorSelectionCriteria;
    private UserVerification userVerificationRequirement;

    public void setAuthenticatorSelectionCriteria(AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
        this.authenticatorSelectionCriteria = authenticatorSelectionCriteria;
    }

    public void setUserVerificationRequirement(io.hanko.sdk.models.UserVerification userVerificationRequirement) {
        this.userVerificationRequirement = userVerificationRequirement;
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelectionCriteria() {
        return authenticatorSelectionCriteria;
    }

    public UserVerification getUserVerificationRequirement() {
        return userVerificationRequirement;
    }
}
