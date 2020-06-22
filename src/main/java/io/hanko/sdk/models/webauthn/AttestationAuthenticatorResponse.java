package io.hanko.sdk.models.webauthn;

public class AttestationAuthenticatorResponse extends AuthenticatorResponse {
    private String attestationObject;

    public void setAttestationObject(String attestationObject) {
        this.attestationObject = attestationObject;
    }

    public String getAttestationObject() {
        return attestationObject;
    }
}
