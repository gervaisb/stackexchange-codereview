package q209963;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;

public class BankAccount {
    private final String id;
    private final Person owner;
    private double balance;
    private final double interest;
    private final InterestType interestType;
    private final Queue<String> operations;

    public BankAccount(Person owner, double interest, InterestType interestType){
        this.owner = owner;
        this.interest = interest;
        this.interestType = interestType;
        this.id = UUID.randomUUID().toString();
        this.operations = new ArrayDeque<>(5);
    }

    public BankAccount(String firstName, String lastName, int age,double interest, InterestType interestType ){
        this(new Person(firstName,lastName,age),interest,interestType);
    }

    public void add(double money){
        this.balance += money;
        addOperation(String.format("%s %.2f%n", "added", money));
    }
    public boolean withdraw(double money) throws InsufficientFundsException {
        if(this.interest > 1){
            return false;
        }
        if(money > this.balance){
            throw new InsufficientFundsException("Not enough money to complete operation");
        }

        this.balance -= money;
        addOperation(String.format("%s %.2f%n", "withdrawn", money));
        return true;
    }

    public List<String> getHistory(){
        List<String> operationsList = new ArrayList<>(this.operations);
        addOperation(String.format("%s%n", "viewed history"));
        return operationsList;
    }

    public boolean transfer(BankAccount account, double amount) throws InsufficientFundsException {
        boolean withdrawn = withdraw(amount);
        if(withdrawn){
            account.add(amount);
            addOperation(String.format("%s %.2f to %s %n","transferred",amount,account.getId()));
        }
        return withdrawn;
    }

    public String getId() {
        return this.id;
    }

    public Person getOwner() {
        return this.owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getInterest() {
        return this.interest;
    }

    public InterestType getInterestType() {
        return this.interestType;
    }

    private void addOperation(String operation){
        if(this.operations.size() == 5){
            this.operations.remove();
        }
        this.operations.add(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
