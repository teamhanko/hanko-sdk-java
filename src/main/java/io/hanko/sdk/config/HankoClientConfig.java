package io.hanko.sdk.config;

import io.hanko.sdk.exception.HankoClientException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Encapsulates main configuration parameters for creating a {@link io.hanko.sdk.webauthn.HankoWebAuthnClient}.
 */
public class HankoClientConfig {
    private final URL apiUrl;
    private final String apiKeyId;
    private final String apiSecret;

    /**
     * Construct a HankoClientConfig.
     * <p>
     * A config created without a Hanko API key ID defaults to API authentication using the
     * the apiSecret.
     *
     * @param apiUrl non-null, the Hanko API base URL, must be a valid URL
     * @param apiSecret non-null, the Hanko API secret
     */
    public HankoClientConfig(String apiUrl, String apiSecret) {
        this(apiUrl, apiSecret, null);
    }

    /**
     * Construct a HankoClientConfig.
     * <p>
     * A config created without a Hanko API key ID defaults to API authentication using an
     * HMAC.
     *
     * @param apiUrl non-null, the Hanko API base URL, must be a valid URL
     * @param apiSecret non-null, the Hanko API secret
     * @param apiKeyId the Hanko API key ID
     */
    public HankoClientConfig(String apiUrl, String apiSecret, String apiKeyId) {
        try {
            this.apiUrl = new URL(apiUrl);
        } catch (MalformedURLException ex) {
            throw new HankoClientException("apiUrl must be valid URL", ex);
        }
        this.apiSecret = Objects.requireNonNull(apiSecret, "apiSecret must not be null");
        this.apiKeyId = apiKeyId;
    }

    /**
     * Get the Hanko API base URL.
     * @return the API base URL as a String
     */
    public URL getApiUrl() {
        return apiUrl;
    }

    /**
     * Get the Hanko API key ID.
     * @return the API Key ID as a String
     */
    public String getApiKeyId() {
        return apiKeyId;
    }

    /**
     * Get the Hanko API secret.
     * @return the API secret as a String
     */
    public String getApiSecret() {
        return apiSecret;
    }
}
