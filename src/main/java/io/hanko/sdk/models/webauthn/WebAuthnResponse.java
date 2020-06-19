package io.hanko.sdk.models.webauthn;

public abstract class WebAuthnResponse {
    String id;
    String rawId;
    String type;
    AuthenticatorResponse response;
}
