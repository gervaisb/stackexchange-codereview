package q188306;

import java.math.BigDecimal;

public class AccountsService {

    private final AccountFactory factory;
    private final AccountsRepository repository;

    public AccountsService(AccountFactory factory, AccountsRepository repository) {
        this.repository = repository;
        this.factory = factory;
    }

    public AccountId create() {
        Account account = factory.create();
        repository.save(account);
        return account.id;
    }

    /**
     * Perform a deposit of the given amount of money to an identified account.
     * @param money     The amount of money. NotNull
     * @param accountId The id of the account. NotNull
     * @throws InvalidAmountException if the amount is lower or equal to zero
     * @throws java.util.NoSuchElementException if there is no Account for the
     *      given AccountId
     */
    public void deposit(BigDecimal money, AccountId accountId) throws InvalidAmountException {
        if ( money.signum()<1 ) {
            throw new InvalidAmountException(money);
        }

        Account account = repository.get(accountId);
        account.deposit(money);
        repository.save(account);
    }

    /**
     * Withdraw the given amount from the account identified by AccountId
     * @param amount    Amount of money to withdraw.
     * @param accountId Id of the account to withdraw from.
     * @throws WithdrawLimitExceededException when the daily withdraw limit
     *  of the account is exceeded.
     * @throws InvalidAmountException if the amount is lower or equal to zero
     * @throws WithdrawLimitExceededException if the daily limit is exceeded
     * @throws java.util.NoSuchElementException if there is no Account for the
     *      given AccountId
     */
    public void withdraw(BigDecimal amount, AccountId accountId) throws InvalidAmountException, WithdrawLimitExceededException {
        if ( amount.signum()<1 ) {
            throw new InvalidAmountException(amount);
        }

        Account account = repository.get(accountId);
        account.withdraw(amount);
        repository.save(account);
    }

    public BigDecimal getBalance(AccountId accountId) {
        return repository.get(accountId).getBalance();
    }

}
