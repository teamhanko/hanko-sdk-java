package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Request for updating a credential.
 */
public class CredentialUpdateRequest {
    private String name;

    /**
     * Construct a CredentialUpdateRequest.
     */
    public CredentialUpdateRequest() {
    }

    /**
     * Construct a CredentialUpdateRequest.
     * @param name the new name for a credential
     */
    @JsonCreator
    public CredentialUpdateRequest(String name) {
        this.name = name;
    }

    /**
     * Get the name.
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * @param name the name as a String
     */
    public void setName(String name) {
        this.name = name;
    }
}
