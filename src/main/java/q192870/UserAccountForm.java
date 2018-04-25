package q192870;

import java.util.Optional;

interface UserAccountForm {

    String getLastName();

    String getFirstName();

    Optional<String> getRole();

    String getEmail();

    String getPassword();

    void setLastNameInvalid(boolean invalid);

    void setFirstNameInvalid(boolean invalid);

    void setRoleInvalid(boolean invalid);

    void setEmailInvalid(boolean invalid);

    void setPasswordInvalid(boolean invalid);

    void setPasswordTooShort(boolean tooShort);

    boolean isTermsAgreementSelected();

    void setTermsAgreementVisible(boolean visible);
}
