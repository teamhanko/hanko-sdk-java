package io.hanko.sdk.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AttestationConveyancePreference {
    NONE("none"),
    INDIRECT("indirect"),
    DIRECT("direct");

    String attestationConveyancePreference;

    AttestationConveyancePreference(String attestationConveyancePreference) {
        this.attestationConveyancePreference = attestationConveyancePreference;
    }

    @JsonValue
    public String getAttestationConveyancePreference() {
        return attestationConveyancePreference;
    }
}
