package q188306;


public class AccountFactory {

    public Account create() {
        AccountId id = new AccountId();
        return new Account(id);
    }
}
