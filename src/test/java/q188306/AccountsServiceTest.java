package q188306;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountsServiceTest {

    private final AccountFactory factory = mock(AccountFactory.class);
    private final AccountsRepository repository = mock(AccountsRepository.class);
    private final AccountsService target = new AccountsService(factory, repository);

    private final Account account = spy(new Account(new AccountId()));

    @Test
    public void create_must_persist_account_and_return_his_id() {
        when(factory.create()).thenReturn(account);
        AccountId result = target.create();

        assertThat(result)
                .isEqualTo(account.id);
        verify(repository).save(account);
    }

    @Test
    public void deposit_must_validate_amount() {
        assertThatCode(()->{
            target.deposit(BigDecimal.ZERO, account.id);
        }).isInstanceOf(InvalidAmountException.class);
        assertThatCode(()->{
            target.deposit(BigDecimal.valueOf(-1), account.id);
        }).isInstanceOf(InvalidAmountException.class);
    }

    @Test
    public void deposit_must_deposit_to_account() throws InvalidAmountException {
        when(repository.get(account.id)).thenReturn(account);

        target.deposit(BigDecimal.TEN, account.id);
        verify(account).deposit(BigDecimal.TEN);
    }

    @Test
    public void withdraw_must_validate_amount() {
        assertThatCode(()->{
            target.withdraw(BigDecimal.ZERO, account.id);
        }).isInstanceOf(InvalidAmountException.class);
        assertThatCode(()->{
            target.withdraw(BigDecimal.valueOf(-1), account.id);
        }).isInstanceOf(InvalidAmountException.class);
    }

    @Test
    public void withdraw_must_withdraw_to_account() throws InvalidAmountException, WithdrawLimitExceededException {
        when(repository.get(account.id)).thenReturn(account);

        target.withdraw(BigDecimal.TEN, account.id);
        verify(account).withdraw(BigDecimal.TEN);
    }

    @Test
    public void getBalance_must_returns_account_balance() {
        when(repository.get(account.id)).thenReturn(account);
        when(account.getBalance()).thenReturn(BigDecimal.TEN);

        BigDecimal result = target.getBalance(account.id);
        verify(account).getBalance();
        assertThat(result)
                .isEqualTo(BigDecimal.TEN);
    }
}