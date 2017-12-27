package q183675;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class AccountTest {

    private final Account target = new Account();


    @Test
    public void transfer_must_move_amount() {
        Account michael = new Account();
        Account max = new Account();

        michael.transfer(15, max);

        assertThat(max.getBalance()).isEqualTo(15);
    }
}