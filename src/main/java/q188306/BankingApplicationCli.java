package q188306;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

class BankingApplicationCli {
    private static final int DEPOSIT = 1;
    private static final int WITHDRAW = 2;
    private static final int CHANGE = 3;
    private static final int EXIT = 4;

    private final AccountsService service;
    private final Scanner scanner;
    private final PrintWriter out;
    private AccountId accountId = null;

    public BankingApplicationCli(InputStream in, OutputStream out, AccountsService service) {
        this.scanner = new Scanner(in);
        this.out = new PrintWriter(out);
        this.service = service;
    }

    public void run() {
        doAccountSelectionOrCreation();
        doMainLoop();
    }

    private void doMainLoop() {
        int action;
        do {
            out.printf("ACCOUNT : %1$s  BALANCE : %2$f%n"+
                       "Actions :  1 - Deposit, 2 - Withdraw, 3 - Change account, 4 - Exit%n" ,
                    accountId.value, service.getBalance(accountId));
            out.flush();
            action = scanner.nextInt();
            switch (action) {
                case DEPOSIT:
                    doDeposit();
                    break;
                case WITHDRAW:
                    doWithdraw();
                    break;
                case CHANGE:
                    doAccountSelectionOrCreation();
                    break;
                default:
                    out.println("[Warn] Unknown action");
                    break;
            }
        } while ( action!=EXIT );
    }

    private void doAccountSelectionOrCreation() {
        do {
            out.println("Enter Account id or leave blank to create one :");
            out.flush();
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                accountId = service.create();
            } else {
                accountId = AccountId.valueOf(input);
            }
        } while (accountId==null);
    }

    private void doDeposit() {
        try {
            service.deposit(readAmount(), accountId);
        } catch (InvalidAmountException e) {
            out.println("[Warn] Invalid deposit amount, must be greater than zero.");
        }
    }

    private void doWithdraw() {
        try {
            service.withdraw(readAmount(), accountId);
        } catch (InvalidAmountException e) {
            out.println("[Warn] Invalid withdraw amount, must be greater than zero.");
        } catch (WithdrawLimitExceededException e) {
            out.println("[Warn] Daily withdraw limit exceeded.");
        }
    }

    private BigDecimal readAmount() {
        out.print("> Enter amount : ");
        out.flush();
        return scanner.nextBigDecimal();
    }
}
