package q192870;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Consumer;

class UserAccountController {

    private final Consumer<EntrepreneurModel> processor;
    private final UserAccountValidator validator;
    private final Clock clock;

    public UserAccountController(Clock clock, UserAccountValidator validator, Consumer<EntrepreneurModel> processor) {
        this.processor = processor;
        this.validator = validator;
        this.clock = clock;
    }

    public void create(UserAccountForm form) {
        Violations violations = validator.validate(form);
        if (!violations.isEmpty()) {
            show(violations, form);
        } else if (!form.isTermsAgreementSelected()) {
            form.setTermsAgreementVisible(true);
        } else {
            process(form);
        }
    }

    private void show(Violations violations, UserAccountForm form) {
        violations.forEach((Object field, Set<String> messages) -> {
            if (field.equals("lastname")) {
                form.setLastNameInvalid(true);
            } else if (field.equals("firstname")) {
                form.setFirstNameInvalid(true);
            } else if (field.equals("role")) {
                form.setRoleInvalid(true);
            } else if (field.equals("email")) {
                form.setEmailInvalid(true);
            } else if (field.equals("password")) {
                form.setPasswordInvalid(messages.contains("invalid"));
                form.setPasswordTooShort(messages.contains("too_short"));
            } else {
                throw new UnsupportedOperationException("Cannot show error for unknown field : \"" + field + "\".");
            }
        });
    }

    private void process(UserAccountForm form) {
        @SuppressWarnings("ConstantConditions") // getRole() presence is verified by the validator
        EntrepreneurModel model = new EntrepreneurModel(form.getLastName(),
                form.getFirstName(), form.getRole().get(), form.getEmail(),
                form.getPassword(), LocalDateTime.now(clock));
        processor.accept(model);
    }

}
