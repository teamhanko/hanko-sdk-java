package io.hanko.sdk.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;

public class HmacUtil {
    private final MessageDigest digest;
    private final String hmacAlgorithm = "HmacSHA256";
    private final String hashAlgorithm = "SHA-256";

    public HmacUtil() {
        try {
            this.digest = MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String makeAuthorizationHeader(String apiKey, String apiKeyId, String method, String path, String content) throws NoSuchAlgorithmException, InvalidKeyException {
        if(apiKeyId == null) {
            return "secret " + apiKey;
        } else {
            return makeHmacAuthorizationHeader(apiKey, apiKeyId, method, path, content);
        }
    }

    private String makeHmacAuthorizationHeader(String apikey, String apikeyId, String method, String path, String content) throws NoSuchAlgorithmException, InvalidKeyException {
        String date = Instant.now().toString();
        String nonce = UUID.randomUUID().toString();
        java.util.Base64.Encoder base64Encoder = java.util.Base64.getEncoder().withoutPadding();

        String stringToSign = String.format("%s:%s:%s:%s:%s", apikeyId, date, method, path, nonce);

        if(content != null && !content.equals("")) {
            byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
            String hashHexed = HankoUtils.asHex(hash);
            stringToSign = String.format("%s:%s", stringToSign, hashHexed);
        }

        SecretKeySpec keySpec = new SecretKeySpec(apikey.getBytes(StandardCharsets.UTF_8), hmacAlgorithm);
        Mac mac = Mac.getInstance(hmacAlgorithm);
        mac.init(keySpec);

        byte[] signatureBytes = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String signatureHex = HankoUtils.asHex(signatureBytes);

        String jsonRepresentation = String.format("{\"apiKeyId\":\"%s\",\"time\":\"%s\",\"nonce\":\"%s\",\"signature\":\"%s\"}",
                apikeyId, date, nonce, signatureHex);

        String base64Representation = base64Encoder.encodeToString(jsonRepresentation.getBytes(StandardCharsets.UTF_8));

        return "HANKO " + base64Representation;
    }
}
