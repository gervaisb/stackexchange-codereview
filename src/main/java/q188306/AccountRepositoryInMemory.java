package q188306;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class AccountRepositoryInMemory implements AccountsRepository {
    private final Map<AccountId, Account> memory = new HashMap<>(2);
    @Override
    public void save(Account account) {
        if ( account!=null ) {
            memory.put(account.id, account);
        }
    }

    @Override
    public Account get(AccountId accountId) {
        if ( memory.containsKey(accountId) ) {
            return memory.get(accountId);
        } else {
            throw new NoSuchElementException("Account #"+accountId);
        }
    }
}
