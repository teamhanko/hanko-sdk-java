# UAF

## Basic Usage

### Registration

#### 1. Get a UAF registration request
```java
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

#### 2. UAF client call
The `hankoRequest.request` is used as an argument for a UAF client.

#### 3. Validate a UAF client registration result
The response from the UAF client then can be send back to the Hanko API as shown below:
```java
UafValidationRequest uafValidationRequest = new UafValidationRequest();
uafValidationRequest.setUafResponse("<UAF_RESPONSE>");
try {
    hankoClient.validateUafOperation("<REQUEST_ID>", uafValidationRequest);
} catch (Exception ex) {
    // react appropriate
}
```

### Authentication

#### 1. Get a UAF authentication request
```java
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

#### 2. UAF client call
The `hankoRequest.request` is used as an argument for a UAF client.

#### 3. Validate a UAF client authentication result
The response from the UAF client then can be send back to the Hanko API as shown below:
```java
UafValidationRequest uafValidationRequest = new UafValidationRequest();
uafValidationRequest.setUafResponse("<UAF_RESPONSE>");
try {
    hankoClient.validateUafOperation("<REQUEST_ID>", uafValidationRequest);
} catch (Exception ex) {
    // react appropriate
}
```
