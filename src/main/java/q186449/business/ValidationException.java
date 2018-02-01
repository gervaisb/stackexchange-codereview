package q186449.business;

import java.util.Set;

public class ValidationException extends Exception {

    static void maybeThrow(Set<String> errors) throws ValidationException {
        if ( !errors.isEmpty() ) {
            throw new ValidationException(errors);
        }
    }

    private final Set<String> errors;

    public ValidationException(Set<String> errors) {
        super("Object is invalid");
        this.errors = errors;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
