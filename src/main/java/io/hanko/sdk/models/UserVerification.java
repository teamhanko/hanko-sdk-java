package io.hanko.sdk.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserVerification {
    REQUIRED("required"),
    PREFERRED("preferred"),
    DISCOURAGED("discouraged");

    String userVerification;

    UserVerification(String uv) {
        this.userVerification = uv;
    }

    @JsonValue
    public String getUserVerification() {
        return userVerification;
    }
}
