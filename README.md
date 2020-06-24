# Hanko Java SDK

[ ![Download](https://api.bintray.com/packages/hanko/jvm-sdks/java/images/download.svg) ](https://bintray.com/hanko/jvm-sdks/java/_latestVersion)

## Installation

This Library is distributed through [JCenter](https://bintray.com/bintray/jcenter).

### Gradle
```groovy
implementation 'io.hanko:java-sdk:<LATEST_VERSION>'
```

### Maven
```xml
<dependency>
  <groupId>io.hanko</groupId>
  <artifactId>java-sdk</artifactId>
  <version>LATEST-VERSION</version>
</dependency>
```

## Usage

```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("https://api.hanko.io", "<API_KEY_ID>", "<API_KEY>");
HankoClient hankoClient = HankoUtils.createHankoClient(hankoClientConfig);
```

### WebAuthn

The usage for WebAuthentication is shown [here](/docs/WebAuthn.md)

### UAF

The usage for UAF is shown [here](/docs/Uaf.md)
