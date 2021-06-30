# Hanko Java SDK

This package is maintained by [Hanko](https://hanko.io).

## Contents

1. [Introduction](#introduction)
1. [Documentation](#documentation)
1. [Installation](#installation)
1. [Usage](#usage)
    1. [Prerequisites](#prerequisites)
    1. [Create a new Hanko API Client](#create-a-new-hanko-api-client)
        1. [Using a custom HttpClient](#using-a-custom-http-client)
    1. [Register a WebAuthn credential](#register-a-webauthn-credential)
    1. [Authenticate with a registered WebAuthn credential](#authenticate-with-a-registered-webauthn-credential)
    1. [Making Transactions](#making-transactions)
    1. [Credential Management](#credential-management)
1. [Exception handling](#exception-handling)
1. [Enable debug logging](#enable-debug-logging)
1. [Support](#support)

## Introduction
This SDK provides an API client that lets you communicate with the
[Hanko Authentication API](https://docs.hanko.io/overview)
to easily integrate [FIDO®](https://fidoalliance.org)-based authentication into your web application written in
Java.

## Documentation

- [Hanko Documentation](https://docs.hanko.io) website
- Hanko SDK [Javadoc](https://javadoc.io/doc/io.hanko/java-sdk)
- Hanko Authentication [API reference](https://docs.hanko.io/api/webauthn)

## Installation

### Gradle
```groovy
implementation 'io.hanko:java-sdk:2.x.x'
```

### Maven
```xml
<dependency>
  <groupId>io.hanko</groupId>
  <artifactId>java-sdk</artifactId>
  <version>2.x.x</version>
</dependency>
```

## Usage

### Prerequisites

In order to utilize the client provided by the SDK you need an API URL as well as API credentials in the form of an 
API key ID and an API secret. View our [getting started](https://docs.hanko.io/gettingstarted) guide in the official 
documentation on how to obtain these.

### Create a new Hanko API Client

Once you have set up your account, create a `HankoClientConfig` with the API URL, the API Key Id and the API secret and 
use it to construct a `HankoWebAuthnClient`.

```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("<API_URL>", "<API_SECRET>", "<API_KEY_ID>");
HankoWebAuthnClient hankoClient = new HankoWebAuthnClient(hankoClientConfig);
```

#### Using a custom HttpClient

The SDK uses [Apache HttpComponents](https://hc.apache.org/httpcomponents-client-4.5.x/index.html) as the underlying 
HTTP client. You can provide a customized HttpClient when constructing the `HankoWebAuthnClient`:

```java
HankoClientConfig hankoClientConfig = new HankoClientConfig("<API_URL>", "<API_SECRET>", "<API_KEY_ID>");
CloseableHttpClient customHttpClient = HttpClients.custom()
        .setProxy(...) // e.g. if you want to proxy requests
        // more customizations
        .build();
HankoWebAuthnClient hankoClient = new HankoWebAuthnClient(hankoClientConfig, customHttpClient);
```

See [here](https://github.com/apache/httpcomponents-client/blob/4.5.x/httpclient/src/examples/org/apache/http/examples/client/ClientConfiguration.java)
for more client configuration examples.

### Register a WebAuthn credential

Registration of a WebAuthn credential involves retrieving credential creation options from the Hanko API 
(initialization), passing these options to the browser's Web Authentication API and lastly sending the WebAuthn response
back to the Hanko API for validation (finalization).

For a more complete example of the authentication process, see the 
[implementation guide](https://docs.hanko.io/implementation/registration) in the Hanko documentation.

#### Registration initialization:

##### Using defaults
```java
// To create the user object you'll need a userId and a userName. The userName usually 
// comes either from a form a user provides when registering for the first time, or from your existing session 
// store or database, as well as a related userId, which may needs to be generated if it is a new user. 
String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"
String userName string;  // e.g. "alice@example.com"

RegistrationInitializationUser user = new RegistrationInitializationUser(userId, userName);

RegistrationInitializationRequest request = new RegistrationInitializationRequest(user);
String initializationResponse = hankoClient.initializeRegistration(request);
```

##### Modifying registration options

You can modify the default credential creation options for registration as follows:

```java
String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"
String userName string;  // e.g. "alice@example.com"

RegistrationInitializationUser user = new RegistrationInitializationUser(userId, userName);

AuthenticatorSelectionCriteria selectionCriteria = new AuthenticatorSelectionCriteria();
selectionCriteria.setAuthenticatorAttachment(AuthenticatorAttachment.PLATFORM);
selectionCriteria.setUserVerification(UserVerificationRequirement.REQUIRED);
selectionCriteria.setRequireResidentKey(false);

RegistrationInitializationOptions options = new RegistrationInitializationOptions();
options.setAttestation(AttestationConveyancePreference.DIRECT);
options.setAuthenticatorSelection(selectionCriteria);

RegistrationInitializationRequest request = new RegistrationInitializationRequest(user, options);
String initializationResponse = hankoClient.initializeRegistration(request);
```

#### Pass Hanko API response to Web Authentication API

Initialization with the Hanko API returns a response that represent
[`PublicKeyCredentialCreationOptions`](https://developer.mozilla.org/en-US/docs/Web/API/PublicKeyCredentialCreationOptions) 
that must be provided to the WebAuthn Authentication API to create a credential. The WebAuthn Authentication API 
requires data that looks like JSON but contains binary data, represented as ArrayBuffers, that needs to be encoded. 
So we can't pass the Hanko API `initializationResponse` directly as `PublicKeyCredentialCreationOptions`, but you can 
use the [Hanko WebAuthn Library](https://github.com/teamhanko/hanko-webauthn) that wraps the WebAuthn Authentication API
and encodes / decodes the data and allows you to easily pass Hanko API responses to the WebAuthn Authentication API 
and vice versa.

You can provide the `initializationResponse` to the `create` function of the 
[Hanko WebAuthn Library](https://github.com/teamhanko/hanko-webauthn) for creating a credential. For a more complete 
example of the registration process, see the [implementation guide](https://docs.hanko.io/implementation/registration) 
in the Hanko documentation.

#### Registration finalization:

Pass the Web Authentication API response as returned from the Hanko WebAutn Library's `create` function to 
the `finalizeRegistration` client method.

```java
String webAuthnResponse = "{\"id\": \"ATIihVy...\", ...}";
FinalizationResponse finalizationResponse = hankoClient.finalizeRegistration(webAuthnResponse);
```

### Authenticate with a registered WebAuthn credential

For a more complete example of the authentication process, see the 
[implementation guide](https://docs.hanko.io/implementation/authentication) in the Hanko documentation.

#### Authentication initialization

##### Using defaults

```java
String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"

AuthenticationInitializationUser user = new AuthenticationInitializationUser(userId)

AuthenticationInitializationRequest request = new AuthenticationInitializationRequest().
request.setUser(user);

String initializationResponse = hankoClient.initializeAuthentication(request)
```

##### Modifying authentication options

You can modify the default credential request options for authentication as follows:

```java
String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"

AuthenticationInitializationUser user = new AuthenticationInitializationUser(userId)

AuthenticationInitializationRequest request = new AuthenticationInitializationRequest().
request.setUser(user);
request.setUserVerification(userVerification);
request.setAuthenticatorAttachment(authenticatorAttachment);

String initializationResponse = hankoClient.initializeAuthentication(request)
```

#### Pass Hanko API response to Web Authentication API

You can provide the `initializationResponse` to the `get` function of the 
[Hanko WebAuthn Library](https://github.com/teamhanko/hanko-webauthn) for authenticating with a credential. For a more 
complete example of the authentication process, see the [implementation guide](https://docs.hanko.io/implementation/authentication) 
in the Hanko documentation.

#### Authentication finalization

Pass the Web Authentication API response as returned from the Hanko WebAutn Library's `get` function to the 
`finalizeAuthentication` client method.

```java
String webAuthnResponse = "{\"id\": \"DaNOpBx...\", ...}";
FinalizationResponse finalizationResponse = hankoClient.finalizeAuthentication(webAuthnResponse)
```

### Making transactions

A transaction is technically the equivalent of an authentication, with the difference being that when initializing 
a transaction, a `transactionText` can be included, which becomes part of the authentication challenge.

#### Transaction initialization

##### Using defaults

```java
String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"

AuthenticationInitializationUser user = new AuthenticationInitializationUser(userId)

TransactionInitializationRequest request = new TransactionInitializationRequest("Pay 5$ to Bob?");
String initializationResponse = hankoClient.InitializeTransaction(request);
```

#### Pass Hanko API response to Web Authentication API

You can provide the `initializationResponse` to the `get` function of the 
[Hanko WebAuthn Library](https://github.com/teamhanko/hanko-webauthn) for authenticating with a credential. For a more 
complete example of the authentication process, see the [implementation guide](https://docs.hanko.io/implementation/authentication) 
in the Hanko documentation.

#### Transaction finalization

Pass the Web Authentication API response as returned from the Hanko WebAutn Library's `get` function to the 
`finalizeTransaction` client method.

```java
String webAuthnResponse = "{\"id\": \"fSmpQnC...\", ...}";
FinalizationResponse finalizationResponse = hankoClient.finalizeTransaction(webAuthnResponse)
```

### Credential management

```java
String credentialId;// e.g. "AQohBypyLBrx8R_UO0cWQuu7hhRGv7bPRRGtbQLrjl..."

// Get all details of the specified credential.
WebAuthnCredential credential = hankoClient.getCredential(credentialId);

// Update the name of a credential.
CredentialUpdateRequest updateRequest = new CredentialUpdateRequest();
updateRequest.setName("MySecurityKey"); // the new name for the WebAuthnCredential
WebAuthnCredential updatedCredential = hankoClient.updateCredential(credentialId, updateRequest);

// Delete the specified credential.
Boolean deleted = hankoClient.deleteCredential(credentialId);

// Search for credentials filtering by userId and paginating results.
CredentialQuery query = new CredentialQuery();
query.setUserId("65a3eba6-22cb-4c35-9881-b21fac6acfd0");
query.setPageSize(15);
query.setPage(1);

List<WebAuthnCredentials> credentials = hankoClient.listCredentials(query);
```

### Exception handling

```
import io.hanko.sdk.exception.HankoException;

try {
  String userId; // e.g. "65a3eba6-22cb-4c35-9881-b21fac6acfd0"
  String userName string;  // e.g. "alice@example.com"

  RegistrationInitializationUser user = new RegistrationInitializationUser(userId, userName);

  RegistrationInitializationRequest request = new RegistrationInitializationRequest(user);
  String initializationResponse = hankoClient.initializeRegistration(request);
} catch (final HankoException ex) {
    System.err.println(ex);
}
```

For more exception types that allow for a more granular exception handling see the `io.hanko.sdk.exception` package and 
the Javadoc for the `HankoWebAuthnClient`.

### Enable debug logging

The SDK uses SLF4J for debug logging. SLF4J is a facade/abstraction for common logging frameworks 
(e.g. java.util.logging, logback, log4j). You need to provide the desired logger implementation you want to use on your 
classpath. See the [SFL4J User Manual](http://slf4j.org/manual.html) for more information on logging configuration.



