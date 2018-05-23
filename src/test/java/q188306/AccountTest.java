package q188306;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    private final Account target = new Account(new AccountId());


    @Test(expected = AssertionError.class)
    public void deposit_must_reject_negative_amount() {
        target.deposit(BigDecimal.valueOf(-1));
    }

    @Test(expected = AssertionError.class)
    public void deposit_must_reject_null_amount() {
        target.deposit(BigDecimal.ZERO);
    }

    @Test
    public void deposit_must_increase_balance() {
        BigDecimal initial = target.getBalance();
        target.deposit(BigDecimal.TEN);

        assertThat(target.getBalance())
                .isGreaterThan(initial)
                .isEqualTo(initial.add(BigDecimal.TEN));
    }

    @Test(expected = AssertionError.class)
    public void withdraw_must_reject_negative_amount() throws WithdrawLimitExceededException {
        target.withdraw(BigDecimal.valueOf(-1));
    }

    @Test(expected = AssertionError.class)
    public void withdraw_must_reject_null_amount() throws WithdrawLimitExceededException {
        target.withdraw(BigDecimal.ZERO);
    }

    @Test
    public void withdraw_must_decrease_balance() throws WithdrawLimitExceededException {
        BigDecimal initial = target.getBalance();
        target.withdraw(BigDecimal.TEN);

        assertThat(target.getBalance())
                .isEqualTo(initial.subtract(BigDecimal.TEN));
    }

    @Test
    public void withdraw_must_honor_daily_withdrawal_limit() throws WithdrawLimitExceededException {
        target.withdraw(target.getDailyLimit());

        assertThatCode(()->{
            target.withdraw(BigDecimal.valueOf(1));
        }).isInstanceOf(WithdrawLimitExceededException.class);
    }

    @Test
    public void getBalance_must_return_current_balance() throws WithdrawLimitExceededException {
        target.deposit(BigDecimal.TEN);
        target.deposit(BigDecimal.TEN);
        target.withdraw(BigDecimal.ONE);

        assertThat(target.getBalance())
                .isEqualTo(BigDecimal.valueOf(19));

    }
}