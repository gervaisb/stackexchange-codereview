package q188306;

import java.math.BigDecimal;

class WithdrawLimitExceededException extends Exception {
    public WithdrawLimitExceededException(BigDecimal dailyLimit) {

    }
}
