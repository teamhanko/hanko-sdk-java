package io.hanko.sdk;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.hanko.sdk.http.HankoHttpClient;
import io.hanko.sdk.http.HankoHttpClientFactory;
import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.json.HankoJsonParserFactory;
import io.hanko.sdk.json.jackson.CustomAuthenticatorResponseDeserializer;
import io.hanko.sdk.models.*;
import io.hanko.sdk.models.webauthn.AuthenticatorResponse;
import io.hanko.sdk.models.webauthn.WebAuthnValidationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.security.InvalidParameterException;

public class HankoClient {

    private HankoHttpClientFactory httpClientFactory;
    private HankoJsonParser jsonParser;
    private HankoClientConfig hankoClientConfig;
    private Logger logger;
    private SimpleModule jacksonModule;
    private ObjectMapper objectMapper = new ObjectMapper();

    public HankoClient(HankoHttpClientFactory httpClientFactory, HankoJsonParserFactory jsonParserFactory, HankoClientConfig hankoClientConfig) {
        this.httpClientFactory = httpClientFactory;
        this.jsonParser = jsonParserFactory.create();
        this.hankoClientConfig = hankoClientConfig;
        this.logger = LoggerFactory.getLogger(HankoClient.class);

        this.jacksonModule = new SimpleModule();
        this.jacksonModule.addDeserializer(AuthenticatorResponse.class, new CustomAuthenticatorResponseDeserializer());
        this.objectMapper.registerModule(this.jacksonModule);
    }

    private <T> T postOperation(String url, Object createRequest, Class<T> responseType) {
        HankoHttpClient httpClient = httpClientFactory.create(hankoClientConfig);

        String json = jsonParser.serialize(createRequest);
        logger.debug("POST operation at '{}' with '{}'", url, json);
        InputStream is = httpClient.post(url, json);

        T response = jsonParser.parse(is, responseType);
        httpClient.close();

        return response;
    }

    private <T> T putOperation(String url, Object createRequest, Class<T> responseType) {
        HankoHttpClient httpClient = httpClientFactory.create(hankoClientConfig);

        String json = jsonParser.serialize(createRequest);
        logger.debug("PUT operation at '{}' with '{}'", url, json);
        InputStream is = httpClient.put(url, json);

        T response = jsonParser.parse(is, responseType);
        httpClient.close();

        return response;
    }

    private <T> T getOperation(String url, Class<T> responseType) {
        HankoHttpClient httpClient = httpClientFactory.create(hankoClientConfig);

        logger.debug("GET operation at '{}'", url);
        InputStream is = httpClient.get(url);

        T response = jsonParser.parse(is, responseType);
        httpClient.close();

        return response;
    }

    public HankoRequest requestUafOperation(CreateUafRequest request) throws InvalidParameterException {
        ValidationResult result = request.isValid();
        if (!result.isValid()) {
            throw new InvalidParameterException(result.getMessage());
        }
        return postOperation("/v1/uaf/requests", request, HankoRequest.class);
    }

    public HankoRequest validateUafOperation(String requestId, UafValidationRequest uafValidationRequest) {
        return putOperation("/v1/uaf/requests/" + requestId, uafValidationRequest, HankoRequest.class);
    }

    public HankoRequest getUafRequest(String requestId) {
        return getOperation("/v1/uaf/requests/" + requestId, HankoRequest.class);
    }

    public HankoRequest requestWebAuthnOperation(CreateWebAuthnRequest request) {
        ValidationResult result = request.isValid();
        if (!result.isValid()) {
            throw new InvalidParameterException(result.getMessage());
        }
        return postOperation("/v1/webauthn/requests", request, HankoRequest.class);
    }

    public HankoRequest validateWebAuthnRequest(String requestId, WebAuthnValidationRequest webauthnValidationRequest) {
        return putOperation("/v1/webauthn/requests/" + requestId, webauthnValidationRequest, HankoRequest.class);
    }

    public HankoRequest validateWebAuthnRequest(String requestId, String webauthnValidationRequestJSON) throws JsonProcessingException {
        WebAuthnValidationRequest webAuthnValidationRequest = this.objectMapper.readValue(webauthnValidationRequestJSON, WebAuthnValidationRequest.class);
        return validateWebAuthnRequest(requestId, webAuthnValidationRequest);
    }

    public HankoRequest getWebAuthnRequest(String requestId) {
        return getOperation("/v1/webauthn/requests/" + requestId, HankoRequest.class);
    }

    public Device[] getRegisteredDevices(String userId) {
        return getOperation("/mgmt/v1/registrations/" + userId, Device[].class);
    }

    public RenameDevice renameDevice(String deviceId, RenameDevice newName) {
        return postOperation("/mgmt/v1/registrations/rename/" + deviceId, newName, RenameDevice.class);
    }
}
