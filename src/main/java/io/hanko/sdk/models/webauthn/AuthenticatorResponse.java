package io.hanko.sdk.models.webauthn;

public abstract class AuthenticatorResponse {
    protected String clientDataJSON;

    public void setClientDataJSON(String clientDataJSON) {
        this.clientDataJSON = clientDataJSON;
    }

    public String getClientDataJSON() {
        return clientDataJSON;
    }
}
