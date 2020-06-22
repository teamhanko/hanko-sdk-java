package io.hanko.sdk.models.webauthn;

public class WebAuthnResponse {
    private String id;
    private String rawId;
    private String type;
    private AuthenticatorResponse response;

    public void setId(String id) {
        this.id = id;
    }

    public void setRawId(String rawId) {
        this.rawId = rawId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResponse(AuthenticatorResponse response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public String getRawId() {
        return rawId;
    }

    public String getType() {
        return type;
    }

    public AuthenticatorResponse getResponse() {
        return response;
    }
}
