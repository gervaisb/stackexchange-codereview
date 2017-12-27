package q183675;

public class Account {

    double balance;
    String accountId;
    static int nextId = 0;
    static final int ROUTING_NUMBER = 12345;
    String bankName;

    {
        if (ROUTING_NUMBER == 12345) {
            bankName = "USA Bank";
        } else {
            bankName = "Other bank";
        }
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
        double transferAmount = amount;
        int routingNumber = recipient.ROUTING_NUMBER;
            if (routingNumber == ROUTING_NUMBER) {
                System.out.println("Your funds will transfer instantly, you and your recipient share the same bank!");
                System.out.println("Bank name: " + bankName);
            } else {
                System.out.println("Your funds will transfer in 2-3 business days.");
            }
        this.balance -= transferAmount;
        recipient.balance += transferAmount;
    }

    public static String getNextId() {
        return "ACCT #" + nextId++;
    }
}
