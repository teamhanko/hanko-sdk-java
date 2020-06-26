package io.hanko.sdk.models.webauthn;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = AssertionAuthenticatorResponse.class, name = "AssertionAuthenticatorResponse"),
        @JsonSubTypes.Type(value = AttestationAuthenticatorResponse.class, name = "AttestationAuthenticatorResponse")
})
public abstract class AuthenticatorResponse {
    protected String clientDataJSON;

    public void setClientDataJSON(String clientDataJSON) {
        this.clientDataJSON = clientDataJSON;
    }

    public String getClientDataJSON() {
        return clientDataJSON;
    }
}
