/**
 * I have been learning Java for the past two months, and started writing this
 * task about simulating Bank and BankAccount to practise a bit of OOP and
 * exception handling. Problem is I am not confident that the solution is ok.
 * It is working fine, I just think it could be written better, but since I am
 * a novice, I would really appreciate some advice.
 *
 * I cannot yet grasp the difference when I should return true/false on
 * success/failure of a method and when to throw an exception.
 *
 * > <b>Bank account</b> Make a BankAccount class.
 * >  + It should have unique number and store information about its and owner -
 * >    First, Last name and age.
 * >  + It should store information about the balance of the account, the interest
 * >    of the account and the type of the interest (complex and simple year interest).
 * >  + If someone tries to create a bank account with invalid information an
 * >    appropriate exception should be thrown.
 * >  + It should support add, withdraw, transfer and history operations.
 * >  + When the interest is greater than 1% the withdraws are forbidden.
 * >  + It should remember the last 5 operations of the account.
 * >
 * > Make a CLI (Command Line Interpreter) for operating with the Bank accounts
 * > It should have the following functions:
 * >  + create_bank_account It should prompt the user for the needed information
 * >    and if everything is ok it should create a new bank account.
 * >  + show_history It should prompt the user for the bank account number and
 * >    show its history.
 * >  + add_money It should prompt the user for the amount and the bank account
 * >    number.
 * >  + withdraw_money It should prompt the user for the amount and the bank
 * >    account number.
 * >  + transfer_money It should prompt the user for the origin and destination
 * >    bank account numbers and the amount
 * >  + calculate_amount It should prompt the user for bank account number and
 * >    number of months. It should return the amount after the given number of months.
 * >
 * > The Bank It should store the bank accounts.
 * >
 * > Notes: you should create and use your own custom exceptions:
 * >  + InsufficientFundsException
 * >  + NonExistingBankAccountException
 */
package q209963;
