package io.hanko.sdk.models.webauthn;

public class AssertionAuthenticatorResponse extends AuthenticatorResponse {
    private String authenticatorData;
    private String signature;

    public void setAuthenticatorData(String authenticatorData) {
        this.authenticatorData = authenticatorData;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
