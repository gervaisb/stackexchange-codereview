package q188306;


/**
 * Today I went to an interview with the Cleartrip software company. For the
 * first round programming, we need to build a mini bank application.
 *
 * In the question paper they clearly mentioned that no data persistence is
 * required.
 *
 *  + Create an account
 *  + Deposit money
 *  + Withdraw money, honor daily withdrawal limit.
 *  + Check the balance
 *
 *  This is what I did on my own, and I was getting rejected.
 */
class Q188306 {

    public static void main(String[] args) {
        AccountsService service = new AccountsService(new AccountFactory(), new AccountRepositoryInMemory());
        BankingApplicationCli cli = new BankingApplicationCli(System.in, System.out, service);
        cli.run();
    }
}
