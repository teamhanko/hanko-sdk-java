package io.hanko.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CommonCreateRequest {
    protected Operation operation;
    protected String username;
    protected String userId;
    protected ClientData clientData;
    protected String transaction;
    protected String[] deviceIds;
    protected boolean isSecondFactorOnly;

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setDeviceIds(String[] deviceIds) {
        this.deviceIds = deviceIds;
    }

    public void setSecondFactorOnly(boolean secondFactorOnly) {
        isSecondFactorOnly = secondFactorOnly;
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

    public ClientData getClientData() {
        return clientData;
    }

    public String getTransaction() {
        return transaction;
    }

    public String[] getDeviceIds() {
        return deviceIds;
    }

    @JsonProperty("isSecondFactorOnly")
    public boolean isSecondFactorOnly() {
        return isSecondFactorOnly;
    }

    public ValidationResult isValid() {
        if(operation == null) {
            return new ValidationResult(false, "Property operation is missing.");
        } else if(userId == null) {
            return new ValidationResult(false, "Property userId is missing.");
        } else if (userId.trim().equals("")) {
            return new ValidationResult(false, "Property userId must not be empty or only consist of space characters.");
        } else if (transaction != null) {
            if (transaction.trim().equals("")) return new ValidationResult(false, "Property transaction must not only consist of space characters.");
        }

        return new ValidationResult(true, null);
    }
}
