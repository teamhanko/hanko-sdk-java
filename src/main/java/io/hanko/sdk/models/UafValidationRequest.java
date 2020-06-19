package io.hanko.sdk.models;

public class UafValidationRequest {
    private String uafResponse;
    private ClientDeviceKeyInformation deviceKeyInfo;

    public void setUafResponse(String uafResponse) {
        this.uafResponse = uafResponse;
    }

    public void setDeviceKeyInfo(ClientDeviceKeyInformation deviceKeyInfo) {
        this.deviceKeyInfo = deviceKeyInfo;
    }

    public String getUafResponse() {
        return uafResponse;
    }

    public ClientDeviceKeyInformation getDeviceKeyInfo() {
        return deviceKeyInfo;
    }
}
