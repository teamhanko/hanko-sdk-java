package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base representation of a user on whose behalf registration and authentication are performed with the
 * Hanko API.
 */
public class User {
    /**
     * The ID of the user.
     */
    protected String id;
    /**
     * The name of the user.
     */
    protected String name;
    /**
     * The displayName of the user.
     */
    protected String displayName;

    /**
     * Construct a User.
     */
    public User() {
    }

    /**
     * Construct a user
     * @param id the ID of the user
     * @param name the name of the user
     * @param displayName the displayName of the user
     */
    @JsonCreator
    public User(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("displayName") String displayName
    ) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }

    /**
     * Get the user {@link #id}.
     * @return the user {@link #id} as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Set the user {@link #id}.
     * @param id the user ID as a String
     */
    public void setId(String id) {
        this.id = id;
    }
}
