package io.hanko.sdk.models;

public class Device {
    private String deviceId;
    private String keyName;
    private String authenticatorType;
    private String lastUsage;
    private String createdAt;
    private AuthenticatorAttachment authenticatorAttachment;
    private boolean isSecondFactorOnly;
    private String deviceOs;
    private String osVersion;

    public String getDeviceId() {
        return deviceId;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getAuthenticatorType() {
        return authenticatorType;
    }

    public String getLastUsage() {
        return lastUsage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public AuthenticatorAttachment getAuthenticatorAttachment() {
        return authenticatorAttachment;
    }

    public boolean isSecondFactorOnly() {
        return isSecondFactorOnly;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public String getOsVersion() {
        return osVersion;
    }
}
