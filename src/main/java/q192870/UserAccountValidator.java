package q192870;

class UserAccountValidator {

    private static final String PASSWORD_REGEX = "..";
    private static final String EMAIL_REGEX = "..";
    private static final int MIN_PASSWORD_LENGTH = 8;

    Violations validate(UserAccountForm form) {
        Violations v = new Violations();
        if ( form.getLastName().isEmpty() ) {
            v.reject("lastname", "empty");
        }
        if ( form.getFirstName().isEmpty() ) {
            v.reject("firstname", "empty");
        }
        if ( !form.getRole().isPresent() ) {
            v.reject("role", "missing");
        }
        if ( !form.getEmail().matches(EMAIL_REGEX) ) {
            v.reject("email", "invalid");
        }
        if ( !form.getPassword().matches(PASSWORD_REGEX) ) {
            v.reject("password", "invalid");
        }
        if ( form.getPassword().length()<MIN_PASSWORD_LENGTH) {
            v.reject("password", "too_short");
        }

        return v;
    }
}
