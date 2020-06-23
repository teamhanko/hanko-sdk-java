package io.hanko.sdk.models;

/**
 * Class which holds all relevant data to request an web authentication operation.
 */
public class CreateWebAuthnRequest extends CommonCreateRequest {
    private AuthenticatorSelectionCriteria authenticatorSelectionCriteria;
    private String displayName;

    /**
     * Set the AuthenticatorSelectionCriteria to specify which type of authenticator you allow for the operation
     * @param authenticatorSelectionCriteria
     */
    public void setAuthenticatorSelectionCriteria(AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
        this.authenticatorSelectionCriteria = authenticatorSelectionCriteria;
    }

    /**
     * Set the displayName which will be included in the request
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelectionCriteria() {
        return authenticatorSelectionCriteria;
    }

    public String getDisplayName() {
        return displayName;
    }
}
