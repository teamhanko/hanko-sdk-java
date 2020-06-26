package io.hanko.sdk.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.hanko.sdk.models.webauthn.AssertionAuthenticatorResponse;
import io.hanko.sdk.models.webauthn.AttestationAuthenticatorResponse;
import io.hanko.sdk.models.webauthn.AuthenticatorResponse;

import java.io.IOException;

public class CustomAuthenticatorResponseDeserializer extends StdDeserializer<AuthenticatorResponse> {

    public CustomAuthenticatorResponseDeserializer() {
        this(null);
    }

    public CustomAuthenticatorResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AuthenticatorResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode clientDataJSON = node.get("clientDataJSON");

        JsonNode signature = node.get("signature");
        JsonNode authenticatorData = node.get("authenticatorData");

        JsonNode attestationObject = node.get("attestationObject");

        if (attestationObject != null && clientDataJSON != null) {
            AttestationAuthenticatorResponse response = new AttestationAuthenticatorResponse();
            response.setClientDataJSON(clientDataJSON.asText());
            response.setAttestationObject(attestationObject.asText());
            return response;
        } else if (signature != null && authenticatorData != null && clientDataJSON != null) {
            AssertionAuthenticatorResponse response = new AssertionAuthenticatorResponse();
            response.setClientDataJSON(clientDataJSON.asText());
            response.setAuthenticatorData(authenticatorData.asText());
            response.setSignature(signature.asText());
            return response;
        } else {
            return null;
        }
    }
}
