package io.hanko.sdk.models;

/**
 * Class which holds all relevant data to request an UAF operation.
 */
public class CreateUafRequest extends CommonCreateRequest {

    public ValidationResult isValid() {
        if(username == null) {
            return new ValidationResult(false, "Property username is missing.");
        } else if(username.trim().equals("")) {
            return new ValidationResult(false, "Property username must not be empty or only consist of space characters.");
        }

        return super.isValid();
    }
}
