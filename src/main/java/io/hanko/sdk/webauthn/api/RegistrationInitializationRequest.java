package io.hanko.sdk.webauthn.api;

import java.util.Objects;

/**
 * Used to initialize a {@link io.hanko.sdk.webauthn.HankoWebAuthnClient#initializeRegistration(RegistrationInitializationRequest)
 * credential registration operation}.
 */
public class RegistrationInitializationRequest {
    private final RegistrationInitializationUser user;
    private RegistrationInitializationOptions options;

    /**
     * Create a RegistrationInitializationRequest
     * @param user non-null, the user on whose behalf registration of a credential should be performed
     */
    public RegistrationInitializationRequest(RegistrationInitializationUser user) {
        this(user, null);
    }

    /**
     * Create a RegistrationInitializationRequest
     * @param user non-null, the {@link RegistrationInitializationUser} on whose behalf registration of a credential
     *             should be performed
     * @param options additional {@link RegistrationInitializationOptions}
     */
    public RegistrationInitializationRequest(RegistrationInitializationUser user, RegistrationInitializationOptions options) {
        this.user = Objects.requireNonNull(user, "user must not be null");
        this.options = options;
    }

    /**
     * Get the {@link RegistrationInitializationUser}.
     * @return the {@link RegistrationInitializationUser}
     */
    public RegistrationInitializationUser getUser() {
        return user;
    }

    /**
     * Get the {@link RegistrationInitializationOptions}.
     * @return the {@link RegistrationInitializationOptions}
     */
    public RegistrationInitializationOptions getOptions() {
        return options;
    }

    /**
     * Set the {@link RegistrationInitializationOptions}
     * @param options the {@link RegistrationInitializationOptions}
     */
    public void setOptions(RegistrationInitializationOptions options) {
        this.options = options;
    }
}
