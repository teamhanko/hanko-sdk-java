package io.hanko.sdk.webauthn;

import io.hanko.sdk.config.HankoClientConfig;
import io.hanko.sdk.http.HankoHttpClient;
import io.hanko.sdk.http.HankoHttpResponse;
import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.webauthn.api.*;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;
import java.util.Objects;

import static io.hanko.sdk.webauthn.Endpoints.*;

/**
 * Main client type for performing registration, authentication, transactions and credential management.
 */
public class HankoWebAuthnClient {

    private final HankoHttpClient httpClient;
    private final HankoJsonParser parser = new HankoJsonParser();

    /**
     * Construct a HankoWebAuthnClient.
     * @param hankoClientConfig non-null, the {@link HankoClientConfig}
     */
    public HankoWebAuthnClient(HankoClientConfig hankoClientConfig) {
        this.httpClient = new HankoHttpClient(hankoClientConfig);
    }

    /**
     * Construct a HankoWebAuthnClient using a custom underlying {@link CloseableHttpClient}.
     * @param hankoClientConfig non-null, the {@link HankoClientConfig}
     * @param httpClient the custom {@link CloseableHttpClient}
     */
    public HankoWebAuthnClient(HankoClientConfig hankoClientConfig, CloseableHttpClient httpClient) {
        this.httpClient = new HankoHttpClient(hankoClientConfig, httpClient);
    }

    /**
     * Construct a HankoWebAuthnClient using a custom {@link HankoHttpClient}.
     * @param httpClient the custom {@link HankoHttpClient}
     */
    public HankoWebAuthnClient(HankoHttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
    }

    /**
     * Initializes the registration of a new credential using a {@link RegistrationInitializationRequest}.
     * On successful initialization, the Hanko Authentication API returns a stringified JSON representation
     * of the CredentialCreationOptions for use with the WebAuthn API's
     * navigator.credentials.create() function.
     *
     * @param request the {@link RegistrationInitializationRequest}
     * @return the CredentialCreationOptions as a String
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public String initializeRegistration(RegistrationInitializationRequest request) {
        String body = parser.serialize(request);
        HankoHttpResponse response = httpClient.post(REGISTRATION_INITIALIZE, body);
        return response.getContent();
    }

    /**
     * Finalizes a registration using the response from the WebAuthn API's navigator.credentials.create() call.
     * @param webAuthnResponse the WebAuthn API response
     * @return the {@link FinalizationResponse}
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public FinalizationResponse finalizeRegistration(String webAuthnResponse) {
        HankoHttpResponse response = httpClient.post(REGISTRATION_FINALIZE, webAuthnResponse);
        return parser.deserialize(response.getStream(), FinalizationResponse.class);
    }

    /**
     * Initializes an authentication with a registered credential using an {@link AuthenticationInitializationRequest}.
     * On successful initialization, the Hanko Authentication API returns a stringified JSON representation
     * of the CredentialRequestOptions for use with the WebAuthn API's navigator.credentials.get() function.
     * @param request the {@link AuthenticationInitializationRequest}
     * @return the String
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public String initializeAuthentication(AuthenticationInitializationRequest request) {
        String body = parser.serialize(request);
        HankoHttpResponse response = httpClient.post(AUTHENTICATION_INITIALIZE, body);
        return response.getContent();
    }

    /**
     * Finalizes an authentication using the response from the WebAuthn API's navigator.credentials.get() call.
     * @param webAuthnResponse the WebAuthn API response as a String
     * @return the {@link FinalizationResponse}
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public FinalizationResponse finalizeAuthentication(String webAuthnResponse) {
        HankoHttpResponse response = httpClient.post(AUTHENTICATION_FINALIZE, webAuthnResponse);
        return parser.deserialize(response.getStream(), FinalizationResponse.class);
    }

    /**
     * Initializes a transaction. A transaction operation is analogous to the authentication operation,
     * with the main difference being that a transaction context must be provided in the form of a string. This value
     * will become part of the challenge an authenticator signs over during the operation.
     * <p>
     * Initialize a transaction using a TransactionInitializationRequest.
     * On successful initialization, the Hanko Authentication API returns a stringified JSON representation
     * of the CredentialRequestOptions for use with the WebAuthn API's navigator.credentials.get() function.
     * @param request the {@link TransactionInitializationRequest}
     * @return the CredentialRequestOptions as a String
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public String initializeTransaction(TransactionInitializationRequest request) {
        String body = parser.serialize(request);
        HankoHttpResponse response = httpClient.post(TRANSACTION_INITIALIZE, body);
        return response.getContent();
    }

    /**
     * Finalizes the transaction request using the response from the WebAuthn API's navigator.credentials.get() call.
     * @param webAuthnResponse the WebAuthn API response as a String
     * @return a {@link FinalizationResponse}
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public FinalizationResponse finalizeTransaction(String webAuthnResponse) {
        HankoHttpResponse response = httpClient.post(TRANSACTION_FINALIZE, webAuthnResponse);
        return parser.deserialize(response.getStream(), FinalizationResponse.class);
    }

    /**
     * Retrieves the registered {@link WebAuthnCredential} with the specified credentialId.
     * @param credentialId the id of the credential to retrieve
     * @return the {@link WebAuthnCredential}
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     * @throws io.hanko.sdk.exception.HankoNotFoundException if no credential was found
     */
    public WebAuthnCredential getCredential(String credentialId) {
        HankoHttpResponse response = httpClient.get(CREDENTIALS + "/" + credentialId);
        return parser.deserialize(response.getStream(), WebAuthnCredential.class);
    }

    /**
     * Retrieves a list of registered {@link WebAuthnCredential}s.
     * <p>
     * Credentials can be filtered by userId and results paginated using a {@link CredentialQuery}.
     * If the {@link CredentialQuery} is null or if any of its values are null, retrieval defaults to
     * searching for credentials for all users with page equal to 1 and page size equal to 10.
     * @param query the {@link CredentialQuery}, nullable
     * @return the list of registered {@link WebAuthnCredential}s
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public List<WebAuthnCredential> listCredentials(CredentialQuery query) {
        String path = query == null ? CREDENTIALS : CREDENTIALS + query.toQueryString();
        HankoHttpResponse response = httpClient.get(path);
        return parser.deserializeList(response.getStream(), WebAuthnCredential.class);
    }

    /**
     * Deletes the {@link WebAuthnCredential} with the specified credentialId.
     * @param credentialId the id of the credential to delete
     * @return true if deletion was successful, otherwise false
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     * @throws io.hanko.sdk.exception.HankoNotFoundException if no credential was found
     */
    public boolean deleteCredential(String credentialId) {
        HankoHttpResponse response = httpClient.delete(CREDENTIALS +  "/" + credentialId);
        return response.getStatusCode() == HttpStatus.SC_NO_CONTENT;
    }

    /**
     * Updates the {@link WebAuthnCredential} with the specified credentialId. Provide a {@link CredentialUpdateRequest}
     * with the updated data. Currently, you can only update the name of a Credential.
     * @param credentialId the id of the credential to update
     * @param request the {@link CredentialUpdateRequest}
     * @return the updated {@link WebAuthnCredential}
     * @throws io.hanko.sdk.exception.HankoClientException on serialization/deserialization errors
     * @throws io.hanko.sdk.exception.HankoApiConnectionException on Hanko API connection failure
     * @throws io.hanko.sdk.exception.HankoAuthenticationException on unauthorized Hanko API call
     */
    public WebAuthnCredential updateCredential(String credentialId, CredentialUpdateRequest request) {
        String body = parser.serialize(request);
        HankoHttpResponse response = httpClient.put(CREDENTIALS + "/" + credentialId, body);
        return parser.deserialize(response.getStream(), WebAuthnCredential.class);
    }
}
