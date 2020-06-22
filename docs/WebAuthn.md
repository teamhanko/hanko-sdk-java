# WebAuthn

## Basic Usage

### Registration
 
#### 1. Get a WebAuthn registration request
You can get a Webauthn registration request as shown below:
```java
CreateWebAuthnRequest createWebAuthnRequest = new CreateWebAuthnRequest();
createWebAuthnRequest.setUserId("<USER_ID>");
createWebAuthnRequest.setDisplayName("<DISPLAY_NAME>");
createWebAuthnRequest.setOperation(Operation.REG);

try {
    HankoRequest hankoRequest = hankoClient.requestWebAuthnOperation(createWebAuthnRequest);
} catch (Exception ex) {
    // react appropriate
}
```

#### 2. WebAuthn API call
The `hankoRequest.request` is used as an argument for the WebAuthentication Credential API `navigator.credentials.create`.

#### 3. Validate a WebAuthn registration result
The result from the WebAuthentication Credential API call then can be send back to the Hanko API as shown below:
```java
AttestationAuthenticatorResponse attestationAuthenticatorResponse = new AttestationAuthenticatorResponse();
attestationAuthenticatorResponse.setClientDataJSON("<CLIENT_DATA_JSON>");
attestationAuthenticatorResponse.setAttestationObject("<ATTESTATION_OBJECT>");

WebAuthnResponse webAuthnResponse = new WebAuthnResponse();
webAuthnResponse.setId("<ID>");
webAuthnResponse.setRawId("<RAW_ID>");
webAuthnResponse.setType("<TYPE>");
webAuthnResponse.setResponse(attestationAuthenticatorResponse);

ClientDeviceKeyInformation clientDeviceKeyInformation = new ClientDeviceKeyInformation();
clientDeviceKeyInformation.setKeyName("<KEY_NAME>");

WebAuthnValidationRequest webAuthnValidationRequest = new WebAuthnValidationRequest();
webAuthnValidationRequest.setWebAuthnResponse(webAuthnResponse);
webAuthnValidationRequest.setDeviceKeyInfo(clientDeviceKeyInformation); // OPTIONAL

try {
    HankoRequest hankoRequest = hankoClient.validateWebAuthnRequest("<REQUEST_ID>", webAuthnValidationRequest);
    // check if `hankoRequest.status == Status.OK`
} catch (Exception ex) {
    // react appropriate
}
```

### Authentication

#### 1. Get a WebAuthn authentication request
You can get a WebAuthn authentication request as shown below:
```java
CreateWebAuthnRequest createWebAuthnRequest = new CreateWebAuthnRequest();
createWebAuthnRequest.setUserId("<USER_ID>");
createWebAuthnRequest.setDisplayName("<DISPLAY_NAME>");
createWebAuthnRequest.setOperation(Operation.AUTH);

try {
    HankoRequest hankoRequest = hankoClient.requestWebAuthnOperation(createWebAuthnRequest);
} catch (Exception ex) {
    // react appropriate
}
```
#### 2. WebAuthn API call
The `hankoRequest.request` is used as an argument for the WebAuthentication Credential API `navigator.credentials.get`.

#### 3. Validate a WebAuthn authentication result
The result from the WebAuthentication Credential API call then can be send back to the Hanko API as shown below:
```java
AssertionAuthenticatorResponse assertionAuthenticatorResponse = new AssertionAuthenticatorResponse();
assertionAuthenticatorResponse.setClientDataJSON("<CLIENT_DATA_JSON>");
assertionAuthenticatorResponse.setAuthenticatorData("<AUTHENTICATOR_DATA>");
assertionAuthenticatorResponse.setSignature("<SIGNATURE>");

WebAuthnResponse webAuthnResponse = new WebAuthnResponse();
webAuthnResponse.setId("<ID>");
webAuthnResponse.setRawId("<RAW_ID>");
webAuthnResponse.setType("<TYPE>");
webAuthnResponse.setResponse(assertionAuthenticatorResponse);

ClientDeviceKeyInformation clientDeviceKeyInformation = new ClientDeviceKeyInformation();
clientDeviceKeyInformation.setKeyName("<KEY_NAME>");

WebAuthnValidationRequest webAuthnValidationRequest = new WebAuthnValidationRequest();
webAuthnValidationRequest.setWebAuthnResponse(webAuthnResponse);
webAuthnValidationRequest.setDeviceKeyInfo(clientDeviceKeyInformation); // OPTIONAL

try {
    HankoRequest hankoRequest = hankoClient.validateWebAuthnRequest("<REQUEST_ID>", webAuthnValidationRequest);
    // check if `hankoRequest.status == Status.OK`
} catch (Exception ex) {
    // react appropriate
}
```