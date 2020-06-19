package io.hanko.sdk.models;

public class ClientDeviceKeyInformation {
    private String pushToken;
    private String secret;
    private String keyName;
    private String deviceOs;
    private String osVersion;
    private String manufacturer;
    private String model;

    /* SETTER */

    /**
     *
     * @param pushToken
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /* GETTER */

    public String getPushToken() {
        return pushToken;
    }

    public String getSecret() {
        return secret;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }
}
