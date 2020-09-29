package io.hanko.sdk.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthenticatorAttachment {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");

    private String attachment;

    AuthenticatorAttachment(String authenticatorAttachment) {
        this.attachment = authenticatorAttachment;
    }

    @JsonValue
    public String getAttachment() {
        return attachment;
    }
}
