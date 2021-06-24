package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hanko.sdk.webauthn.protocol.AuthenticatorAttachment;

/**
 * Representation of an authenticator associated with an existing credential.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authenticator {
    private final String aaguid;
    private final String name;

    /**
     * Indicates the {@link AuthenticatorAttachment} modality used on registration of a credential with this
     * authenticator.
     */
    private final AuthenticatorAttachment attachment;

    /**
     * Construct an Authenticator.
     * <p>
     * Generally, there is no need for SDK clients to ever instantiate an authenticator. The authenticator
     * is part of a {@link WebAuthnCredential}, which is part of the Hanko API responses and deserialized
     * by the SDK.
     * @param aaguid the {@link #getAaguid aaguid} of the authenticator
     * @param name the {@link #getName name} of the authenticator
     * @param attachment {@link #getAttachment() authenticatorAttachment} used on registration
     */
    @JsonCreator
    public Authenticator(
            @JsonProperty("aaguid") String aaguid,
            @JsonProperty("name") String name,
            @JsonProperty("attachment") AuthenticatorAttachment attachment) {
        this.aaguid = aaguid;
        this.name = name;
        this.attachment = attachment;
    }

    /**
     Get the AAGUID of this authenticator.
     * @return the AAGUID as a String. The Authenticator Attestation Globally Unique ID is a 128-bit identifier
     * indicating the type (e.g. make and model) of the authenticator.
     */
    public String getAaguid() {
        return aaguid;
    }

    /**
     * Get the authenticator name.
     * @return the name of the authenticator as given by the authenticator manufacturer as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the {@link AuthenticatorAttachment}.
     * @return {@link AuthenticatorAttachment} modality used on registration of a credential with this
     * authenticator.
     */
    public AuthenticatorAttachment getAttachment() {
        return attachment;
    }
}
