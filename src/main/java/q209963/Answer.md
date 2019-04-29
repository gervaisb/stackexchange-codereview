Hello Michaela,

This is already a good piece of code, continue like that.

Here are some improvements that I can imagine for your code:

**Exceptions:**
Except a typo into the name of `NonExistingBankAccountException` I would suggest 
to move the construction of the message in the exception itself. So that you 
will always have the same message and your code is more concise:

`new NonExistingBankAccountException(String accountId)` 

**Bank:**
1. I read in your requirements that _"It should store information about [..] the 
interest [..] and the type of the interest"_

But I have to admit that having a field to store th interest is a bit strange
double interest

2. There is a good OO subject into your `calculateAmount` methods:
How can you get rid of this switch ? The `switch` expression is useful but may 
be a code smell in some case. And it can be a code smell in your case.

Imagine that you want to add another `InterestType`. You should change this enum 
AND your `Bank` class. What can you do to preserve the _single responsibility principe_ ?

**BankAccount:**
1. For the withdraw forbidden rule when interest is greater than 1% I will also 
use an exception but provide a test method to verify if we can withdraw. 
Having a interest under % is then a pre-condition of the `withdraw` method.

2. Operations.
Instead of storing a list of strings, you can view the operations as executable treatment 
on the account. So you are not obliged to maintains the state of the account but 
just storing his operations history and playing them to have the current state. 
This a kind of _event sourcing_ that I suggest in another similar question : https://codereview.stackexchange.com/a/188370/115154

**CommandLineInterpreter:**
There is a typo in `showOpitons`. 

**Person:**
It is usually easier to store the birth date instead of the age. Because age 
change in time while teh birth date don't. And you can always compute the age 
from the birth date. 


**Your question:**
For your question:
> I cannot yet grasp the difference when I should return true/false on
> success/failure of a method and when to throw an exception.

I would just say that exceptions are for exceptional cases, an exception occur 
when you encounter and invalid state but they should never be used to control 
the flow of your program while booleans can.

This is sometimes more clear when you use reason with pre and post conditions. 
An exception is thrown when a pre condition is not matched:

    /**
     * Attempt to remove the given amount from this account balance.
     * @param amount a positive double indicating the amount to be withdrawn. `> -1`.
     * @return true when the amount has been withdrawn. Otherwise false.
     * @throw InvalidAmountException iff the amount is a nagtive number.
    boolean withdraw(double amount) {
      // ...
    }
    
The param has a precondition of being positive. If this precondition is not met, 
then you may receive an exception. However for some reasons it happens that 
everything is fine but the withdrawal cannot be executed. Then you get a `false` 
because this is no due to a violation of the contract or invalid state.
