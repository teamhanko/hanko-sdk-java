# Hanko Java SDK

The Java SDK simplifies the usage of [Hanko](https://hanko.io). 

## Usage

### Webauthn

#### Register
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

#### Authenticate
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

### Hanko Authenticator

#### Register
```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("https://api.hanko.io", "<API_KEY_ID>", "<API_KEY>");
HankoClient hankoClient = HankoUtils.createHankoClient(hankoClientConfig);

CreateHankoAuthenticatorRequest createHankoRequest = new CreateHankoAuthenticatorRequest();
createHankoRequest.setUserId("<USER_ID>");
createHankoRequest.setUsername("<USERNAME>");
createHankoRequest.setOperation(Operation.REG);

try {
    HankoRequest hankoRequest = hankoClient.requestUafOperation(createHankoRequest);
} catch (Exception ex) {
    // react appropriate
}
```

#### Authenticate
```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("https://api.hanko.io", "<API_KEY_ID>", "<API_KEY>");
HankoClient hankoClient = HankoUtils.createHankoClient(hankoClientConfig);

CreateHankoAuthenticatorRequest createHankoRequest = new CreateHankoAuthenticatorRequest();
createHankoRequest.setUserId("<USER_ID>");
createHankoRequest.setUsername("<USERNAME>");
createHankoRequest.setOperation(Operation.AUTH);

try {
    HankoRequest hankoRequest = hankoClient.requestUafOperation(createHankoRequest);
} catch (Exception ex) {
    // react appropriate
}
```