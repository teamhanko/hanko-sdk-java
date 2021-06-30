package io.hanko.sdk.webauthn;

/**
 * Provides Hanko API endpoint paths.
 */
public class Endpoints {

    private Endpoints() {
    }

    /**
     * Hanko API endpoint path for initializing a registration.
     */
    public static final String REGISTRATION_INITIALIZE = "/v1/webauthn/registration/initialize";

    /**
     * Hanko API endpoint path for finalizing a registration.
     */
    public static final String REGISTRATION_FINALIZE = "/v1/webauthn/registration/finalize";

    /**
     * Hanko API endpoint path for initializing an authentication.
     */
    public static final String AUTHENTICATION_INITIALIZE = "/v1/webauthn/authentication/initialize";

    /**
     * Hanko API endpoint path for finalizing an authentication.
     */
    public static final String AUTHENTICATION_FINALIZE = "/v1/webauthn/authentication/finalize";

    /**
     * Hanko API endpoint path for initializing a transaction.
     */
    public static final String TRANSACTION_INITIALIZE = "/v1/webauthn/transaction/initialize";

    /**
     * Hanko API endpoint path for finalizing a transaction.
     */
    public static final String TRANSACTION_FINALIZE = "/v1/webauthn/transaction/finalize";

    /**
     * Hanko API endpoint path for credentials.
     */
    public static final String CREDENTIALS = "/v1/webauthn/credentials";
}