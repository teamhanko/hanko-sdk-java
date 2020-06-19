package io.hanko.sdk.models;

public enum UserVerification {
    REQUIRED("required"),
    PREFERRED("preferred"),
    DISCOURAGED("discouraged");

    String userVerification;

    UserVerification(String uv) {
        this.userVerification = uv;
    }
}
