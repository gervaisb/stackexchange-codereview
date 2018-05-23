package q188306;

public interface AccountsRepository {

    void save(Account account);

    Account get(AccountId accountId);
}
