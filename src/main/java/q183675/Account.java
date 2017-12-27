package q183675;

public class Account {

static double balance;
String accountId;
static int nextId = 0;
static final int ROUTING_NUMBER = 12345;
String bankName;

{
    if (ROUTING_NUMBER == 12345) {
        bankName = "USA Bank";
    }
    else {
        bankName = "Other bank";
    }
}

public void deposit(double amount) {
    balance = balance + amount;
}


public void withdraw(double amount) {
    balance = balance - amount;
}


public void transfer (Customer c1, Customer c2) {
    double transferAmount;
    int routingNumber;
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter transfer amount: ");
        transferAmount = input.nextDouble();

        System.out.println("Please enter recipient's routing number: ");
        routingNumber = input.nextInt();

        if (routingNumber == ROUTING_NUMBER) {
            System.out.println("Your funds will transfer instantly, you and your recipient share the same bank!");
            System.out.println("Bank name: " + bankName);
        } else {
            System.out.println("Your funds will transfer in 2-3 business days.");
        }
    }
    c1.customerBalance -= transferAmount;
    c2.customerBalance += transferAmount;

}
public static String getNextId() {
    return "ACCT #" + nextId++;
}
