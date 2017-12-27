package q183675;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void transfer_must_move_amount() {
        Account michael = new Account(new AccountNumber("12345678"));
        Account max = new Account(new AccountNumber("12349876"));

        michael.transfer(15, max);

        assertThat(max.getBalance()).isEqualTo(15);
    }
}