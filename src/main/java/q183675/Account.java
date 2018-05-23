package q183675;

public class Account {
//    static int nextId = 0;
//    String accountId;
//    String bankName;
//    {
//        if (ROUTING_NUMBER == 12345) {
//            bankName = "USA Bank";
//        } else {
//            bankName = "Other bank";
//        }
//    }


    private final AccountNumber number;
    private double balance = 0.0;

    public Account(AccountNumber number) {
        this.number = number;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public double getBalance() {
        return balance;
    }

    public void transfer(double amount, Account recipient) {
        if (isSameBank(recipient)) {
            System.out.println("Your funds will transfer instantly, you and your recipient share the same bank!");
        } else {
            System.out.println("Your funds will transfer in 2-3 business days.");
        }
        this.withdraw(amount);
        recipient.deposit(amount);
    }

    private boolean isSameBank(Account recipient) {
        return this.number.isSameBank(recipient.number);
    }

}
