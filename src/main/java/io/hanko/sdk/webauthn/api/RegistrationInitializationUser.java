package io.hanko.sdk.webauthn.api;

import java.util.Objects;

/**
 * Represents the user on whose behalf registration of a credential should be performed.
 */
public class RegistrationInitializationUser extends User {

    /**
     * Construct a RegistrationInitializationUser.
     * <p>
     * Automatically sets the user's displayName equal to the the username.
     *
     * @param id non-null, a unique user ID
     * @param name non-null, the username
     */
    public RegistrationInitializationUser(String id, String name) {
        this(id, name, name);
    }

    /**
     * Construct a RegistrationInitializationUser.
     *
     * @param id non-null, a unique user ID
     * @param name non-null, the username
     * @param displayName non-null, the displayName for the user
     */
    public RegistrationInitializationUser(String id, String name, String displayName) {
        super(
                Objects.requireNonNull(id, "id must not be null"),
                Objects.requireNonNull(name, "name must not be null"),
                Objects.requireNonNull(displayName, "displayName must not be null")
        );
    }

    /**
     * Get the username.
     * @return the username as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the displayName for the user.
     * @return the displayName as a String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the displayName for the user.
     * @param displayName the displayName as a String
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
