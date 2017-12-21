package q183272;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

class MonthlyConsumptionReport {

    private final Map<YearAndMonth, BigDecimal> amountByMonth;

    public MonthlyConsumptionReport(Iterable<Consumption> consumptions) {
        amountByMonth = new TreeMap<>(YearAndMonth::compareTo);
        for (Consumption consumption : consumptions) {
            amountByMonth.compute(YearAndMonth.of(consumption), (k, current) -> {
                BigDecimal amount = consumption.getAmount();
                return current == null ? amount : current.add(amount);
            });
        }
    }

    // @formatter:off
    public void writeTo(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("   Month-Year   |  Usage   %n")
              .printf("----------------+----------%n");
        amountByMonth.forEach((yearAndMonth, amount) -> writer
              .printf("%1$15s | %2$#8.2f%n", yearAndMonth, amount)
        );
        writer.flush();
    } // @formatter:on

    static class YearAndMonth implements Formattable, Comparable<YearAndMonth> {
        static YearAndMonth of(Consumption consumption) {
            final LocalDate date = consumption.getDate();
            return new YearAndMonth(date.getYear(), date.getMonth());
        }

        private final Month month;
        private final int year;
        YearAndMonth(int year, Month month) {
            this.month = month;
            this.year = year;
        }

        @Override
        public int compareTo(YearAndMonth o) {
            Comparator<YearAndMonth> compareMonth = Comparator.comparingInt((YearAndMonth x)->x.month.getValue());
            Comparator<YearAndMonth> compareYear = Comparator.comparingInt((YearAndMonth x)->x.year);
            return compareYear.thenComparing(compareMonth).compare(this, o);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            YearAndMonth that = (YearAndMonth) o;
            return year == that.year &&
                    month == that.month;
        }

        @Override
        public int hashCode() {
            return Objects.hash(month, year);
        }

        @Override
        public void formatTo(Formatter formatter, int flags, int width, int precision) {
            formatter.format("%1$-" + width + "s", String.format("%1$tB-%2$d", month, year));
        }
    }
}
