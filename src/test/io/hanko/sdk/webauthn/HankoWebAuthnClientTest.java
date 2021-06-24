package io.hanko.sdk.webauthn;

import io.hanko.sdk.config.HankoClientConfig;
import io.hanko.sdk.exception.HankoClientException;
import io.hanko.sdk.http.HankoHttpClient;
import io.hanko.sdk.http.HankoHttpResponse;
import io.hanko.sdk.webauthn.api.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class HankoWebAuthnClientTest {

    @Mock
    HankoHttpClient httpClient;

    @Mock
    HankoHttpResponse response;

    HankoClientConfig config;

    HankoWebAuthnClient client;

    @BeforeEach
    void setUp() {
        config = new HankoClientConfig("https://localhost:8080", "apiKey");
        client = new HankoWebAuthnClient(httpClient);
    }

    @Test
    void Construct_NullArguments_ThrowException() {
        assertThatThrownBy(() -> new HankoWebAuthnClient(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("must not be null");
    }

    @Test
    void InitializeRegistration_ReturnOptionsString() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        given(response.getContent()).willReturn("credentialCreationOptions");

        RegistrationInitializationUser user = new RegistrationInitializationUser("user", "name", "displayName");
        RegistrationInitializationRequest request = new RegistrationInitializationRequest(user);
        String got = client.initializeRegistration(request);

        assertThat(got).isEqualTo("credentialCreationOptions");
    }

    @Test
    void FinalizeRegistration_ReturnFinalizationResponse() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        File file = new File ("src/test/resources/FinalizationResponse.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        FinalizationResponse got = client.finalizeRegistration("webAuthnResponse");

        assertThat(got.getCredential()).isNotNull();
    }

    @Test
    void InitializeAuthentication_ReturnOptionsString() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        given(response.getContent()).willReturn("credentialRequestOptions");

        AuthenticationInitializationRequest request = new AuthenticationInitializationRequest();
        String got = client.initializeAuthentication(request);

        assertThat(got).isEqualTo("credentialRequestOptions");
    }

    @Test
    void FinalizeAuthentication_ReturnFinalizationResponse() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        File file = new File ("src/test/resources/FinalizationResponse.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        FinalizationResponse got = client.finalizeAuthentication("webAuthnResponse");

        assertThat(got.getCredential()).isNotNull();
    }

    @Test
    void InitializeTransaction_ReturnOptionsString() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        given(response.getContent()).willReturn("credentialRequestOptions");

        TransactionInitializationRequest request = new TransactionInitializationRequest("transactionText");
        String got = client.initializeTransaction(request);

        assertThat(got).isEqualTo("credentialRequestOptions");
    }

    @Test
    void FinalizeTransaction_ReturnFinalizationResponse() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        File file = new File ("src/test/resources/FinalizationResponse.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        FinalizationResponse got = client.finalizeTransaction("webAuthnResponse");

        assertThat(got.getCredential()).isNotNull();
    }

    @Test
    void FinalizeTransaction_InvalidResponse_ThrowException() throws IOException {
        given(httpClient.post(anyString(), anyString())).willReturn(response);
        given(response.getStream()).willReturn(new ByteArrayInputStream("invalid".getBytes(StandardCharsets.UTF_8)));

        assertThatThrownBy(() -> client.finalizeTransaction("webAuthnResponse"))
            .isInstanceOf(HankoClientException.class)
            .hasMessageContaining("Could not deserialize");
    }

    @Test
    void GetCredential_ReturnCredential() throws IOException {
        given(httpClient.get(anyString())).willReturn(response);
        File file = new File ("src/test/resources/WebAuthnCredential.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        WebAuthnCredential got = client.getCredential("credentialId");

        assertThat(got).isNotNull();
    }

    @Test
    void UpdateCredential_ReturnCredential() throws IOException {
        given(httpClient.put(anyString(), anyString())).willReturn(response);
        File file = new File ("src/test/resources/WebAuthnCredential.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        WebAuthnCredential got = client.updateCredential("credentialId", new CredentialUpdateRequest("newName"));

        assertThat(got).isNotNull();
    }

    @Test
    void ListCredentials_ReturnCredentialList() throws IOException {
        given(httpClient.get(anyString())).willReturn(response);
        File file = new File ("src/test/resources/WebAuthnCredentialList.json");
        InputStream content = new FileInputStream(file);
        given(response.getStream()).willReturn(content);

        List<WebAuthnCredential> got = client.listCredentials(null);

        assertThat(got).hasSize(2);
    }

    @Test
    void DeleteCredential_ReturnNoContentResponse() throws IOException {
        given(httpClient.delete(anyString())).willReturn(response);
        given(response.getStatusCode()).willReturn(HttpStatus.SC_NO_CONTENT);

        Boolean got = client.deleteCredential("credentialId");

        assertThat(got).isTrue();
    }
}