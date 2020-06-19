package io.hanko.sdk.models;

public class ValidationResult {

    private boolean isValid;
    private String message;

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
