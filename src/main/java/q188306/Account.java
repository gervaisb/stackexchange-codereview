package q188306;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

class Account {

    private final class Operation {
        private final ZonedDateTime time;
        private final BigDecimal amount;

        Operation(BigDecimal amount) {
            this.amount = amount;
            this.time = ZonedDateTime.now();
        }

        boolean isWithdrawal() {
            return amount.signum()<0;
        }

        boolean isDeposit() {
            return amount.signum()>0;
        }
    }
    private static final BigDecimal DAILY_LIMIT = BigDecimal.valueOf(3000);

    private final List<Operation> history = new ArrayList<>(10);
    public final AccountId id;

    Account(final AccountId id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return history.stream()
                .map(e -> e.amount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getWithdrawAmountOfToday() {
        LocalDate today = LocalDate.now();
        ZonedDateTime yesterday = today.minusDays(1).atStartOfDay(ZoneId.systemDefault());
        return history.stream().filter(Operation::isWithdrawal)
                .filter(e -> e.time.isAfter(yesterday))
                .map(e -> e.amount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .abs();
    }

    public BigDecimal getDailyLimit() {
        return DAILY_LIMIT;
    }

    /**
     * Increase this account balance with the given amount
     * @param amount a positive amount of money to add to this balance. NotNul, GreaterThanZero
     */
    public void deposit(BigDecimal amount) {
        assert amount.signum()>0 : "deposit amount must be positive";
        history.add(new Operation(amount));
    }

    /**
     * Decrease this account balance of the given amount
     * @param amount a positive amount of money to remove from this balance. NotNull, GreaterThanZero
     * @throws WithdrawLimitExceededException if the daily withdraw limit is exceeded. Can be checked
     *      with {@link Account#getDailyLimit()} and {@link Account#getWithdrawAmountOfToday()}
     */
    public void withdraw(BigDecimal amount) throws WithdrawLimitExceededException {
        assert amount.signum()>0 : "withdraw amount must be positive";
        BigDecimal foreseenDailyWithdraw = getWithdrawAmountOfToday().add(amount);
        if ( getDailyLimit().compareTo(foreseenDailyWithdraw)<0 ) {
            throw new WithdrawLimitExceededException(getDailyLimit());
        }
        history.add(new Operation(amount.negate()));
    }


}
