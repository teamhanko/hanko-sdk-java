package io.hanko.sdk.webauthn.api;

/**
 * Used to {@link io.hanko.sdk.webauthn.HankoWebAuthnClient#initializeAuthentication(AuthenticationInitializationRequest)
 * initialize authentication} with an existing credential.
 */
public class AuthenticationInitializationRequest {
    private AuthenticationInitializationUser user;
    private AuthenticationInitializationOptions options;

    /**
     * Construct an AuthenticationInitializationRequest
     */
    public AuthenticationInitializationRequest() {
    }

    /**
     * Construct an AuthenticationInitializationRequest.
     * @param options the {@link AuthenticationInitializationOptions}
     */
    public AuthenticationInitializationRequest(AuthenticationInitializationOptions options) {
        this(null, options);
    }

    /**
     * Construct an AuthenticationInitializationRequest
     * @param user nullable, an {@link AuthenticationInitializationUser};
     *             if {@code null} indicates authentication with a resident key credential
     * @param options the {@link AuthenticationInitializationOptions}
     */
    public AuthenticationInitializationRequest(AuthenticationInitializationUser user, AuthenticationInitializationOptions options) {
        this.user = user;
        this.options = options;
    }

    /**
     * Get the user on whose behalf an authentication should be performed.
     * @return the {@link AuthenticationInitializationUser}
     */
    public AuthenticationInitializationUser getUser() {
        return user;
    }


    /**
     * Set the user on whose behalf an authentication should be performed.
     * @param user the {@link AuthenticationInitializationUser}
     */
    public void setUser(AuthenticationInitializationUser user) {
        this.user = user;
    }

    /**
     * Get the options for initializing an authentication.
     * @return the {@link AuthenticationInitializationOptions}
     */
    public AuthenticationInitializationOptions getOptions() {
        return options;
    }

    /**
     * Set the options for initializing an authentication.
     * @param options {@link AuthenticationInitializationOptions}
     */
    public void setOptions(AuthenticationInitializationOptions options) {
        this.options = options;
    }
}
