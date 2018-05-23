package q188306;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AccountFactoryTest {

    private final AccountFactory target = new AccountFactory();

    @Test
    public void create_should_produce_valid_accounts() {
        Account result = target.create();
        assertThat(result.id).isNotNull();
    }
}