package io.hanko.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HankoRequest {
    private String id;
    private Operation operation;
    private String username;
    private String userId;
    private Status status;
    private String createdAt;
    private String validUntil;
    private RelyingParty relyingParty;
    private ClientData clientData;
    private Location location;
    private Link[] links;
    private String transaction;
    private String request;
    private String deviceID;
    private HankoRequestDevice device;
    private Boolean isSecondFactorOnly;

    public String getId() {
        return id;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public RelyingParty getRelyingParty() {
        return relyingParty;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public Location getLocation() {
        return location;
    }

    public Link[] getLinks() {
        return links;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getRequest() {
        return request;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public HankoRequestDevice getDevice() {
        return device;
    }

    @JsonProperty("isSecondFactorOnly")
    public Boolean isSecondFactorOnly() {
        return isSecondFactorOnly;
    }

    public void setSecondFactorOnly(Boolean secondFactorOnly) {
        isSecondFactorOnly = secondFactorOnly;
    }
}
