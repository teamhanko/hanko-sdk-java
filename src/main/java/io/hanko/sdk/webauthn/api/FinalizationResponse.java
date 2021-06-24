package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response to a registration, authentication or transaction finalization.
 */
public class FinalizationResponse {
    private final WebAuthnCredential credential;

    /**
     * Construct a FinalizationResponse
     * @param credential the created or existing {@link WebAuthnCredential}
     */
    @JsonCreator
    public FinalizationResponse(@JsonProperty("credential") WebAuthnCredential credential) {
        this.credential = credential;
    }

    /**
     * Get the {@link WebAuthnCredential}
     * @return a {@link WebAuthnCredential}
     */
    public WebAuthnCredential getCredential() {
        return credential;
    }
}
