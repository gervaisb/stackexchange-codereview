package q192870;

import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.time.Clock;
import java.util.Optional;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class UserAccountControllerTest {

    private final UserAccountForm form = mock(UserAccountForm.class, AnswersForUserAccount.get());
    private final UserAccountValidator validator = mock(UserAccountValidator.class);
    private final Consumer<EntrepreneurModel> processor = mock(Consumer.class);
    private final UserAccountController target = new UserAccountController(Clock.systemUTC(), validator, processor);

    @Test
    public void should_show_violations_on_form() {
        Violations violations = new Violations();
        violations.reject("lastname", "empty");
        violations.reject("firstname", "empty");
        violations.reject("role", "empty");
        violations.reject("email", "invalid");
        violations.reject("password", "invalid");
        violations.reject("password", "too_short");

        when(validator.validate(form)).thenReturn(violations);

        target.create(form);

        verify(form).setLastNameInvalid(true);
        verify(form).setFirstNameInvalid(true);
        verify(form).setRoleInvalid(true);
        verify(form).setEmailInvalid(true);
        verify(form).setPasswordInvalid(true);
        verify(form).setPasswordTooShort(true);
    }

    @Test
    public void show_terms_agreement_when_not_accepted() {
        when(form.isTermsAgreementSelected()).thenReturn(false);
        when(validator.validate(form)).thenReturn(new Violations());

        target.create(form);

        verify(form).setTermsAgreementVisible(true);
    }

    @Test
    public void process_model() {
        when(form.getRole()).thenReturn(Optional.of("role"));
        when(form.isTermsAgreementSelected()).thenReturn(true);
        when(validator.validate(form)).thenReturn(new Violations());

        target.create(form);

        verify(processor).accept(any(EntrepreneurModel.class));
    }
}