package io.hanko.sdk.http;

import io.hanko.sdk.config.HankoClientConfig;
import io.hanko.sdk.exception.HankoApiConnectionException;
import io.hanko.sdk.exception.HankoApiException;
import io.hanko.sdk.exception.HankoAuthenticationException;
import io.hanko.sdk.exception.HankoNotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HankoHttpClientTest {

    @Mock
    CloseableHttpClient httpClient;

    @Mock
    CloseableHttpResponse response;

    @Mock
    HttpEntity entity;

    @Mock
    StatusLine statusLine;

    HankoClientConfig hmacConfig = new HankoClientConfig("https://localhost:8080", "apiKey", "apiKeyId");

    HankoClientConfig secretConfig = new HankoClientConfig("https://localhost:8080", "apiKey");

    @Test
    void Get_ClientCannotConnect_ThrowException() throws IOException {
        given(httpClient.execute(any(HttpUriRequest.class))).willThrow(ClientProtocolException.class);
        HankoHttpClient client = new HankoHttpClient(secretConfig, httpClient);

        assertThatThrownBy(() -> client.get("/path"))
                .isInstanceOf(HankoApiConnectionException.class)
                .hasMessageContaining("Could not connect");
    }

    @Test
    void Get_ApiRespondsWithUnauthorizedStatus_ThrowException() throws IOException {
        setUp();
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_UNAUTHORIZED);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        assertThatThrownBy(() -> client.get("/path"))
                .isInstanceOf(HankoAuthenticationException.class)
                .hasMessageContaining("API key configuration");
    }

    @Test
    void Get_ApiRespondsWithNotFoundStatus_ThrowException() throws IOException {
        setUp();
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_NOT_FOUND);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        assertThatThrownBy(() -> client.get("/path"))
                .isInstanceOf(HankoNotFoundException.class)
                .hasMessageContaining("Hanko API resource");
    }

    @Test
    void Get_ApiRespondsWithRedirectStatus_ThrowException() throws IOException {
        setUp();
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_MOVED_PERMANENTLY);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        assertThatThrownBy(() -> client.get("/path"))
                .isInstanceOf(HankoApiException.class)
                .hasMessageContaining("Hanko API returned an unexpected status code");
    }

    @Test
    void Get_SecretConfig_ReturnResponse() throws IOException {
        setUp();
        given(entity.getContent()).willReturn(new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8.name())));
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_OK);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(secretConfig, httpClient);

        HankoHttpResponse response = client.get("/get");

        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getMethod().equals("GET")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getURI().toString().equals("https://localhost:8080/get")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> Arrays.stream(r.getHeaders("Authorization")).anyMatch(h -> h.getValue().equals("secret apiKey"))));

        assertThat(response.getContent()).isEqualTo("test");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void Get_HmacConfig_ReturnResponse() throws IOException {
        setUp();
        given(entity.getContent()).willReturn(new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8.name())));
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_OK);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        HankoHttpResponse response = client.get("/get");

        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getMethod().equals("GET")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getURI().toString().equals("https://localhost:8080/get")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> Arrays.stream(r.getHeaders("Authorization")).anyMatch(h -> h.getValue().contains("hanko"))));

        assertThat(response.getContent()).isEqualTo("test");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void Post_HmacConfig_ReturnResponse() throws IOException {
        setUp();
        given(entity.getContent()).willReturn(new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8.name())));
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_OK);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        HankoHttpResponse response = client.post("/post", "body");

        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getMethod().equals("POST")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getURI().toString().equals("https://localhost:8080/post")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> Arrays.stream(r.getHeaders("Authorization")).anyMatch(h -> h.getValue().contains("hanko"))));

        assertThat(response.getContent()).isEqualTo("test");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void Delete_HmacConfig_ReturnResponse() throws IOException {
        setUp();
        given(entity.getContent()).willReturn(null);
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_NO_CONTENT);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        HankoHttpResponse response = client.delete("/delete");

        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getMethod().equals("DELETE")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getURI().toString().equals("https://localhost:8080/delete")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> Arrays.stream(r.getHeaders("Authorization")).anyMatch(h -> h.getValue().contains("hanko"))));

        assertThat(response.getContent()).isEmpty();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void Put_HmacConfig_ReturnResponse() throws IOException {
        setUp();
        given(entity.getContent()).willReturn(new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8.name())));
        given(statusLine.getStatusCode()).willReturn(HttpStatus.SC_OK);
        given(httpClient.execute(any(HttpUriRequest.class))).willReturn(response);
        HankoHttpClient client = new HankoHttpClient(hmacConfig, httpClient);

        HankoHttpResponse response = client.put("/put", "body");

        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getMethod().equals("PUT")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> r.getURI().toString().equals("https://localhost:8080/put")));
        verify(httpClient).execute(argThat((HttpUriRequest r) -> Arrays.stream(r.getHeaders("Authorization")).anyMatch(h -> h.getValue().contains("hanko"))));

        assertThat(response.getContent()).isEqualTo("test");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    private void setUp() {
        given(response.getEntity()).willReturn(entity);
        given(response.getStatusLine()).willReturn(statusLine);
        given(entity.isRepeatable()).willReturn(true);
    }
}