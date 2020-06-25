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

    public String getAuthenticatorData() {
        return authenticatorData;
    }

    public String getSignature() {
        return signature;
    }
}
