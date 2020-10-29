package io.hanko.sdk.models;

import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.json.HankoJsonParserFactory;
import io.hanko.sdk.json.jackson.HankoJsonParserFactoryJackson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateWebAuthnRequestTest {
    
    
    @Test
    void serialize() {
        CreateWebAuthnRequest req = new CreateWebAuthnRequest();
        req.setUserId("randomID");
        req.setUsername("randomUser");
        req.setOperation(Operation.REG);
        HankoJsonParserFactory jsonFac = new HankoJsonParserFactoryJackson();
        HankoJsonParser jsonParser = jsonFac.create();
        String json = jsonParser.serialize(req);
        System.out.println(json);
        assertEquals("{\"attestationConveyancePreference\":null,\"authenticatorSelectionCriteria\":null,\"clientData\":null,\"deviceIds\":null,\"displayName\":null,\"isSecondFactorOnly\":null,\"operation\":\"REG\",\"transaction\":null,\"userId\":\"randomID\",\"username\":\"randomUser\"}",json);
    }

}