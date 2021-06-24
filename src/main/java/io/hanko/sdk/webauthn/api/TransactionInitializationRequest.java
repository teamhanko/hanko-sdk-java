package io.hanko.sdk.webauthn.api;

import java.util.Objects;

/**
 * Used to {@link io.hanko.sdk.webauthn.HankoWebAuthnClient#initializeTransaction(TransactionInitializationRequest)
 * initialize a transaction operation}.
 */
public class TransactionInitializationRequest extends AuthenticationInitializationRequest {
    private String transaction;

    /**
     * Construct a TransactionInitializationRequest.
     * @param transactionText the transaction text as a String
     */
    public TransactionInitializationRequest(String transactionText) {
        super(null, null);
        this.transaction = Objects.requireNonNull(transactionText, "transactionText must not be null");
    }

    /**
     * Get the transaction text.
     * @return the transaction text as a String
     */
    public String getTransaction() {
        return transaction;
    }
}
