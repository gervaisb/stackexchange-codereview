package q183675;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Set<Account> accounts = new HashSet<>();
    public String firstName;
    public String lastName;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public double getBalance() {
        return accounts.stream().mapToDouble(Account::getBalance)
                .sum();
    }

    public void addAccount(double initialBalance) {
        Account account = new Account();
        account.deposit(initialBalance);
        accounts.add(account);
    }

    public void addAccount() {
        addAccount(100);
    }

}
