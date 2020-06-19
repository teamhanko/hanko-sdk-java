package io.hanko.sdk.models.webauthn;

import io.hanko.sdk.models.ClientDeviceKeyInformation;

public class WebAuthnValidationRequest {
    private WebAuthnResponse webAuthnResponse;
    private ClientDeviceKeyInformation deviceKeyInfo;

    public void setWebAuthnResponse(WebAuthnResponse webAuthnResponse) {
        this.webAuthnResponse = webAuthnResponse;
    }

    public void setDeviceKeyInfo(ClientDeviceKeyInformation deviceKeyInfo) {
        this.deviceKeyInfo = deviceKeyInfo;
    }

    public WebAuthnResponse getWebAuthnResponse() {
        return webAuthnResponse;
    }

    public ClientDeviceKeyInformation getDeviceKeyInfo() {
        return deviceKeyInfo;
    }
}
