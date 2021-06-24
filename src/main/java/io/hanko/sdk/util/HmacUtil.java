package io.hanko.sdk.util;

import io.hanko.sdk.exception.HankoClientException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * Utility class for providing an authorization header to use for
 * making authenticated calls to the Hanko API.
 *
 * @see <a href="https://docs.hanko.io/api/webauthn#section/Security">Hanko API security</a>
 */
public class HmacUtil {

    private HmacUtil() {
    }

    /**
     * Construct a suitable authorization header for a request to the Hanko API.
     * <p>
     * If no {@code apiKeyId} is given, the header value consists of the `secret` prefix and the
     * apiSecret as a token value. Otherwise the header value consist of the `hanko` prefix,
     * and a base64 encoded JSON representation including an HMAC signature as the token
     * value.
     *
     * @param apiSecret the Hanko API key as a String
     * @param apiKeyId the Hanko API key ID as a String
     * @param method the HTTP method name used in the request
     * @param path the endpoint path for the request as a String
     * @param content the request body content (if any)
     * @return the complete authorization header as a String
     * @throws HankoClientException if the HMAC could not be calculated
     */
    public static String makeAuthorizationHeader(String apiSecret, String apiKeyId, String method, String path, String content) {
        if (apiKeyId == null) {
            return "secret " + apiSecret;
        } else {
            return makeHmacAuthorizationHeader(apiSecret, apiKeyId, method, path, content);
        }
    }

    private static String makeHmacAuthorizationHeader(String apiSecret, String apiKeyId, String method, String path, String content) {
        try {
            long date = Instant.now().getEpochSecond();
            String nonce = UUID.randomUUID().toString();

            String trimmedPath = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
            String stringToSign = String.format("%s:%d:%s:%s:%s", apiKeyId, date, method, trimmedPath, nonce);

            if (content != null && !content.equals("")) {
                MessageDigest digest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
                byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
                String hashHexed = Hex.encodeHexString(hash);
                stringToSign = String.format("%s:%s", stringToSign, hashHexed);
            }

            SecretKeySpec keySpec = new SecretKeySpec(
                    apiSecret.getBytes(StandardCharsets.UTF_8),
                    HmacAlgorithms.HMAC_SHA_256.getName());
            Mac mac = Mac.getInstance(HmacAlgorithms.HMAC_SHA_256.getName());
            mac.init(keySpec);

            byte[] signatureBytes = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String signatureHex = Hex.encodeHexString(signatureBytes);

            String jsonFormat = "{\"apiKeyId\":\"%s\",\"time\":\"%d\",\"nonce\":\"%s\",\"signature\":\"%s\"}";
            String jsonRepresentation = String.format(jsonFormat, apiKeyId, date, nonce, signatureHex);

            Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();
            String base64Representation = base64Encoder.encodeToString(jsonRepresentation.getBytes(StandardCharsets.UTF_8));

            return "hanko " + base64Representation;
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new HankoClientException("Could not calculate HMAC: " + ex.getMessage(), ex);
        }
    }
}
