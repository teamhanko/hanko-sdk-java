package io.hanko.sdk.webauthn.api;

/**
 * Represents the user on whose behalf authentication with an existing credential should be performed.
 */
public class AuthenticationInitializationUser extends User {

    /**
     * Construct an AuthenticationInitializationUser.
     *
     * Can be used for authentication with a resident key credential.
     */
    public AuthenticationInitializationUser() {
    }

    /**
     * Construct an AuthenticationInitializationUser.
     *
     * @param id nullable, a unique user ID;
     *           if {@code null}, indicates authentication with a resident key credential
     */
    public AuthenticationInitializationUser(String id) {
        super(id, null, null);
    }
}
