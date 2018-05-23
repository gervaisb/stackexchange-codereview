package q183272;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

class Consumption {
    /**
     * @param date   The ISO formatted date. not-null
     * @param amount The consumed amount.
     * @return A new {@link Consumption} of the given amount for the date
     * @throws DateTimeParseException when the date is not ISO formatted
     * @see {@link DateTimeFormatter#ISO_LOCAL_DATE}
     */
    static Consumption of(String date, double amount) {
        return new Consumption(
                LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE),
                BigDecimal.valueOf(amount)
        );
    }

    private final BigDecimal amount;
    private final LocalDate date;

    public Consumption(LocalDate date, BigDecimal amount) {
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumption that = (Consumption) o;
        return amount.compareTo(that.amount)==0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }

    @Override
    public String toString() {
        return String.format("(%1$tY-%1$tm-%1$td, %2$.2f)", date, amount);
    }

}
