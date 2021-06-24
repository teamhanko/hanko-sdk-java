package io.hanko.sdk.webauthn.api;

import io.hanko.sdk.webauthn.protocol.AttestationConveyancePreference;
import io.hanko.sdk.webauthn.protocol.AuthenticatorSelectionCriteria;

/**
 * Allows the setting of additional authenticator attributes for a {@link RegistrationInitializationRequest}.
 */
public class RegistrationInitializationOptions {
    private AuthenticatorSelectionCriteria authenticatorSelection;
    private AttestationConveyancePreference attestation;

    /**
     * Construct RegistrationInitializationOptions.
     */
    public RegistrationInitializationOptions() {
    }

    /**
     * Construct RegistrationInitializationOptions.
     * @param authenticatorSelection nullable, the {@link AuthenticatorSelectionCriteria}
     * @param attestation nullable, the {@link AttestationConveyancePreference}
     */
    public RegistrationInitializationOptions(
            AuthenticatorSelectionCriteria authenticatorSelection,
            AttestationConveyancePreference attestation) {
        this.authenticatorSelection = authenticatorSelection;
        this.attestation = attestation;
    }

    /**
     * Get the {@link AuthenticatorSelectionCriteria}.
     * @return the {@link AuthenticatorSelectionCriteria}
     */
    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return authenticatorSelection;
    }

    /**
     * Set the {@link AuthenticatorSelectionCriteria}.
     * @param authenticatorSelection the {@link AuthenticatorSelectionCriteria}
     */
    public void setAuthenticatorSelection(AuthenticatorSelectionCriteria authenticatorSelection) {
        this.authenticatorSelection = authenticatorSelection;
    }

    /**
     * Get the {@link AttestationConveyancePreference}.
     * @return the {@link AttestationConveyancePreference}
     */
    public AttestationConveyancePreference getAttestation() {
        return attestation;
    }

    /**
     * Set the {@link AttestationConveyancePreference}.
     * @param attestation {@link AttestationConveyancePreference}
     */
    public void setAttestation(AttestationConveyancePreference attestation) {
        this.attestation = attestation;
    }
}
