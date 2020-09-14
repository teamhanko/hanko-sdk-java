package io.hanko.sdk.models;

public enum AttestationConveyancePreference {
    NONE("none"),
    INDIRECT("indirect"),
    DIRCECT("direct");

    String attestationConveyancePreference;

    AttestationConveyancePreference(String uv) {
        this.attestationConveyancePreference = uv;
    }
}
