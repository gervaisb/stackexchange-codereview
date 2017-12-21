package q183272;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyConsumptionReportTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final MonthlyConsumptionReport target = new MonthlyConsumptionReport(
            Arrays.asList(
                    Consumption.of("2017-02-13", 1200.00),
                    Consumption.of("2017-02-19", 50.00),
                    Consumption.of("2017-04-10", 100.45)
            ));

    @Test
    public void must_group_by_month() {

        target.writeTo(output);

        assertThat(output.toString())
                .containsOnlyOnce("February-2017")
                .containsOnlyOnce("April-2017");
    }

    @Test
    public void must_sort_month() {

        target.writeTo(output);

        assertThat(output.toString()).containsSequence(
                "February-2017   |  1250.00",
                "April-2017      |   100.45");
    }

    @Test
    public void must_sum_consumption_by_month() {

        target.writeTo(output);

        assertThat(output.toString())
                .contains("February-2017   |  1250.00")
                .contains("April-2017      |   100.45");
    }
}