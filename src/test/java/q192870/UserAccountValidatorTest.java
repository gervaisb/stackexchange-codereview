package q192870;

import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserAccountValidatorTest {

    private final UserAccountForm form = mock(UserAccountForm.class, AnswersForUserAccount.get());
    private final UserAccountValidator target = new UserAccountValidator();

    @Test
    public void reject_empty_lastName() {
        when(form.getLastName()).thenReturn("");

        assertThat(target.validate(form).getMessagesFor("lastname"))
                .contains("empty");
    }
    @Test
    public void reject_empty_firstName() {
        when(form.getFirstName()).thenReturn("");

        assertThat(target.validate(form).getMessagesFor("firstname"))
                .contains("empty");
    }
    @Test
    public void reject_missing_role() {
        when(form.getRole()).thenReturn(Optional.empty());

        assertThat(target.validate(form).getMessagesFor("role"))
                .contains("missing");
    }
    @Test
    public void reject_invalid_email() {
        when(form.getEmail()).thenReturn("");

        assertThat(target.validate(form).getMessagesFor("email"))
                .contains("invalid");
    }
    @Test
    public void reject_invalid_password() {
        when(form.getPassword()).thenReturn("");

        assertThat(target.validate(form).getMessagesFor("password"))
                .contains("invalid");
    }
    @Test
    public void reject_too_short_password() {
        when(form.getPassword()).thenReturn("");

        assertThat(target.validate(form).getMessagesFor("password"))
                .contains("too_short");
    }
}