package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representation of a credential.
 */
public class WebAuthnCredential {
    private final String id;
    private final User user;
    private final String createdAt;
    private final String lastUsed;
    private final boolean isResidentKey;
    private final String name;
    private final boolean userVerification;
    private final Authenticator authenticator;

    /**
     * Construct a WebAuthnCredential.
     * <p>
     * Generally, there is no need for SDK clients to ever manually instantiate
     * WebAuthnCredentials since they are part of Hanko API responses that are
     * deserialized through the SDK.
     *
     * @param id the {@link #getId() id} of the credential
     * @param user the {@link #getUser() user} associated with the credential
     * @param createdAt the {@link #getCreatedAt() time of creation} of the credential
     * @param lastUsed the {@link #getLastUsed() last usage time} for authentication
     * @param isResidentKey whether this credential is a resident key credential
     * @param userVerification whether this credential was registered with user verification
     * @param name the {@link #getName() name} of the credential
     * @param authenticator the {@link #getAuthenticator() authenticator} used for registration
     */
    @JsonCreator
    public WebAuthnCredential(
            @JsonProperty("id") String id,
            @JsonProperty("user") User user,
            @JsonProperty("createdAt") String createdAt,
            @JsonProperty("lastUsed") String lastUsed,
            @JsonProperty("isResidentKey") boolean isResidentKey,
            @JsonProperty("name") String name,
            @JsonProperty("userVerification") boolean userVerification,
            @JsonProperty("authenticator") Authenticator authenticator) {
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.lastUsed = lastUsed;
        this.isResidentKey = isResidentKey;
        this.name = name;
        this.userVerification = userVerification;
        this.authenticator = authenticator;
    }

    /**
     * Get the credential ID
     * @return the ID of the credential as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Get the {@link User} associated with the credential.
     * @return the user who registered the credential
     */
    public User getUser() {
        return user;
    }

    /**
     * Get the time of credential creation.
     * @return the time of credential creation in date-time notation
     * as defined by RFC 3339, section 5.6
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the last time this credential was used for authentication
     * @return the last usage in date-time notation as defined by RFC 3339, section 5.6
     */
    public String getLastUsed() {
        return lastUsed;
    }

    /**
     * Indicates whether this credential was registered as
     * a resident credential/client-side discoverable credential
     * @return true if it was registered as a resident credential, false otherwise
     */
    @JsonProperty("isResidentKey")
    public boolean isResidentKey() {
        return isResidentKey;
    }

    /**
     * Get the name for the credential.
     *
     * The name has a default value on credential creation but can be changed later
     * through the {@link io.hanko.sdk.webauthn.HankoWebAuthnClient#updateCredential
     * update credential operation}.
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether this credential was registered with a successful user verification
     * process.
     * @return true if the credential was registered with user verification, false otherwise
     */
    public boolean isUserVerification() {
        return userVerification;
    }

    /**
     * Get the {@link Authenticator} used for registering the credential.
     * @return the {@link Authenticator}
     */
    public Authenticator getAuthenticator() {
        return authenticator;
    }
}
