package io.hanko.sdk.models;

public enum FidoType {
    FIDO_UAF("uaf"),
    WEBAUTHN("webauthn");

    private String type;
    FidoType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
