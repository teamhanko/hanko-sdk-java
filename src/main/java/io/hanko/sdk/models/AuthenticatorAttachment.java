package io.hanko.sdk.models;

public enum AuthenticatorAttachment {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross_platform");

    private String attachment;

    AuthenticatorAttachment(String authenticatorAttachment) {
        this.attachment = authenticatorAttachment;
    }

    public String getAttachment() {
        return attachment;
    }
}
