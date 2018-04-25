Sorry to read that you got rejected. Let's try to learn form that.

First of all, try to be consistent in your names and follow the naming conventions. You have a `WithdrawMoney` method 
inside a `CreateAccount` class. A 'command' class like your `CreateAccount` has usually on method that match the intent 
(_execute_, _perform_, _create_). The methods and parameters must start with a lower case letter, `IFSCcode` should be 
`ifscCode` and `WithdrawMoney` must be `withdrawMoney`. This is for the conventions and basic good practices.

`double` is not the best choice when dealing with money, you should better use `BigDecimal`. But in the ideal world you 
may create a `Money(unit, amount)` type.

# Separation of concerns
Your code mix the business concerns with the infrastructure, reading from the command line has nothing do to in the same 
class as the one that manage an account. You should have a look at some commons patterns like _MVC_. 

I would have introduced one `AccountsService` with one method for each use case :
 + Create an account: `create():AccountId` seems strange but since there are no requirements for creation, we keep it 
 simple, see _YAGNI_.
 + Deposit money: `deposit(BigDecimal, AccountId):void`
 + Withdraw money, honor daily withdrawal limit: `withdraw(BigDecimal, AccountId):void ^DailyWithdrawLimitExceededExecption`
 + Check the balance: `getBalance(AccountId):BigDecimal`
 
This service will be used to isolate the business logic. On the other side, a `BankingApplicationCli` may be used to 
work with `InputStream` and `OutputStream` in order to invoke methods on `AccountsService` and print the results. Mine 
will be a simple class with a main loop. The first step would be to select or create a account, then all actions will be 
done on this account, like an ATM.

# Modeling business concepts
I like to do a development that is close to _DDD_; I would have introduced one `Account` which can be retrieve or saved 
from an `AccountsRepository`. This `Account` would have one method for each use case with some _pre-conditions_:

    class Account {
        public final AccountId id;
        
        void deposit(BigDecimal amount) {
            assert amount.signum()>0;
            // ...
        }
        void withdraw(BigDecimal amount) throws DailyWithdrawLimitExceededExecption {
            assert amount.signum()>0;
            // ...
        }
        BigDecimal getBalance() {
            // ...
        }
    }
    
You see that there is a direct match between the methods from `Account` and `AccountsService`. The service will just 
enforce the preconditions by validating the inputs and delegate to the methods on `Account`. This is where you do the 
link between the infrastructure and business (repository is infrastructure but later you maye also add security, 
transactions, logging, ..)

    class AccountsService {
        private final AccountsRepository repository;
        
        void withdraw(BigDecimal amount, AccountId accountId) throws InvalidAmountException, DailyWithdrawLimitExceededExecption {
            if ( amount.signum()<1 ) {
                throw new InvalidAmountException(amount);
            }
            Account account = repository.get(accountId);
            account.withdraw(amount);
            repository.save(account);
        }
        
        BigDecimal getBalance(AccountId accountId) {
            return repository.get(accountId).getBalance();
        }
    } 
    
    
Notice that I use a conventions that use verbs for method that changes the system but looks like getters when querying 
the state. You may not like this conventions but the idea is to highlight the importance of conventions and self 
documented code. 

# Designing the `deposit` and `withdraw` methods
The first idea that I had in mind was to manage one mutable `BigDecimal` field. It works well for the basis of `deposit`
: `this.balance = balance.add(amount)` and withdraw. But then when you have to deal with the daily limit, things are 
more complex. You may hold a date and a sum of daily withdraw but another way is to maintains a `List` of `Operation` 
that have a time and an amount, `deposit` will add a `new Operation(amount)` while `withdraw` add one 
`new Operation(amount.negate())`. 

To compute the daily withdraw, you have to filter all the operations for the current day that are withdraw 
(amount is negative) and sum them. The balance is computed by reducing all the operations via an addition.


    public BigDecimal getBalance() {
        return history.stream()
                .map(e -> e.amount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Decrease this account balance of the given amount
     * @param amount a positive amount of money to remove from this balance. NotNull, GreaterThanZero
     * @throws WithdrawLimitExceededException if the daily withdraw limit is exceeded. Can be checked
     *      via getWithdrawAmountOfToday()
     */
    public void withdraw(BigDecimal amount) throws WithdrawLimitExceededException {
        assert amount.signum()>0 : "withdraw amount must be positive";
        BigDecimal foreseenDailyWithdraw = getWithdrawAmountOfToday().add(amount);
        if ( getDailyLimit().compareTo(foreseenDailyWithdraw)<0 ) {
            throw new WithdrawLimitExceededException(getDailyLimit());
        }
        history.add(new Operation(amount.negate()));
    }
    
    
# Testing
By separating everything you can easily test the behaviors of `Account` and interactions of `AccountsService`. The only 
things that is complex to test is your `BakingApplicationCli`.    
    
    
---
You can find my solution on FILL_ME. I have also added one `AccountFactory` to create a new valid `Account`. This one 
does not does many thing but may evolve in time and isolate the creation process. I have chosen to add a `getDailyLimit` 
method in `Account` so that we can imagine to have on `abstract Account` and many implementations with different limits.
The factory is then the perfect place to choose the implementation. 
  