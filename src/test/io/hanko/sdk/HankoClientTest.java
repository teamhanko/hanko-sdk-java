package io.hanko.sdk;

import io.hanko.sdk.models.CreateUafRequest;
import io.hanko.sdk.models.HankoRequest;
import io.hanko.sdk.models.Operation;
import io.hanko.sdk.models.Status;
import io.hanko.sdk.util.HankoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HankoClientTest {
    private HankoClient client;
    @BeforeEach
    void setUp() {
        String apiUrl = System.getenv("HANKO_API_URL");
        String apiKey = System.getenv("HANKO_API_KEY");
        String apiKeyId = System.getenv("HANKO_API_KEY_ID");
        HankoClientConfig config = new HankoClientConfig(apiUrl,apiKeyId,apiKey);
        client = HankoUtils.createHankoClient(config);
    }

    @Test
    void requestUafOperation() {
        CreateUafRequest req = new CreateUafRequest();
        req.setOperation(Operation.REG);
        req.setUserId("fakeUserId");
        req.setUsername("fakeUser");
        HankoRequest hankoReq = client.requestUafOperation(req);
        assertEquals(hankoReq.getStatus(), Status.PENDING);
    }

    @Test
    void validateUafOperation() {
    }

    @Test
    void getUafRequest() {
    }

    @Test
    void cancelUafRequest() {
        CreateUafRequest req = new CreateUafRequest();
        req.setOperation(Operation.REG);
        req.setUserId("fakeUserId");
        req.setUsername("fakeUser");
        HankoRequest hankoReq = client.requestUafOperation(req);
        assertEquals(hankoReq.getStatus(), Status.PENDING);
        hankoReq = client.cancelUafRequest(hankoReq.getId());
        assertEquals(hankoReq.getStatus(), Status.FAILED);
    }

    @Test
    void requestWebAuthnOperation() {
    }

    @Test
    void validateWebAuthnRequest() {
    }

    @Test
    void testValidateWebAuthnRequest() {
    }

    @Test
    void testValidateWebAuthnRequest1() {
    }

    @Test
    void getWebAuthnRequest() {
    }

    @Test
    void getRegisteredDevices() {
    }

    @Test
    void renameDevice() {
    }
}