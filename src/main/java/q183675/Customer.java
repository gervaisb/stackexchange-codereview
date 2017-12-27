package q183675;

public class Customer {

public String firstName;
public String lastName;
public Account acc;
public double customerBalance = acc.balance;
int defaultBalance = 100;


public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
 }


public Customer() {
    firstName = "John";
    lastName = "Doe";
 }


public void addAccount(double initialBalance) {
    acc = new Account();
    acc.accountId = "ACCT ID: " + Account.getNextId();
    customerBalance = initialBalance;
 }


public void addAccount() {
    addAccount(100);
 }

}
