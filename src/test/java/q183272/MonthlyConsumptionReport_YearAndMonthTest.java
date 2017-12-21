package q183272;

import org.junit.Test;
import q183272.MonthlyConsumptionReport.YearAndMonth;

import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class MonthlyConsumptionReport_YearAndMonthTest {

    private final YearAndMonth target = new YearAndMonth(2017, Month.JANUARY);

    @Test
    public void is_equal_with_same_year_and_month() {
        assertThat(new YearAndMonth(2017, Month.JANUARY))
                .isEqualTo(target)
                .extracting(YearAndMonth::hashCode)
                .isEqualTo(new int[]{target.hashCode()});
    }

    @Test
    public void is_not_equal_with_different_year() {
        assertThat(new YearAndMonth(2016, Month.JANUARY))
                .isNotEqualTo(target)
                .extracting(YearAndMonth::hashCode)
                .isNotEqualTo(new int[]{target.hashCode()});
    }

    @Test
    public void is_not_equal_with_different_month() {
        assertThat(new YearAndMonth(2017, Month.FEBRUARY))
                .isNotEqualTo(target)
                .extracting(YearAndMonth::hashCode)
                .isNotEqualTo(new int[]{target.hashCode()});
    }


    @Test
    public void is_compared_by_year_then_month() {
        assertThat(new YearAndMonth(2017, Month.FEBRUARY)).isBetween(
                new YearAndMonth(2017, Month.JANUARY),
                new YearAndMonth(2018, Month.FEBRUARY));
    }
}