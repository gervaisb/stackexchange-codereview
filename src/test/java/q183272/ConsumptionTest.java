package q183272;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class ConsumptionTest {

    private final Consumption target = new Consumption(LocalDate.of(2017, Month.JANUARY, 19), BigDecimal.TEN);

    @Test
    public void of_must_parse_iso_date() {
        Consumption other = Consumption.of("2017-01-19", 10.0);
        assertEquals(target, other);
    }

    @Test(expected = DateTimeParseException.class)
    public void of_must_reject_malformed_iso_date() {
        Consumption.of("2017/01/19", 10.0);
    }
}