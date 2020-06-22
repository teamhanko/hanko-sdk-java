# Hanko Java SDK

## Usage

```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("https://api.hanko.io", "<API_KEY_ID>", "<API_KEY>");
HankoClient hankoClient = HankoUtils.createHankoClient(hankoClientConfig);
```

### WebAuthn

The usage for WebAuthentication is shown [here](/docs/WebAuthn.md)

### UAF

The usage for UAF is shown [here](/docs/Uaf.md)
