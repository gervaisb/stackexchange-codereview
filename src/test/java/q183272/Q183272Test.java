package q183272;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;


public class Q183272Test {

    private final String input = "((\"2017-02-13\", 1200.00), (\"2017-02-19\", 50.00), (\"2017-04-10\", 100.45))";
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void must_output_the_total_amount_spent_per_month() {
        ConsumptionsReader reader = new ConsumptionsReader(new StringReader(input));
        MonthlyConsumptionReport report = new MonthlyConsumptionReport(reader);

        report.writeTo(output);

        assertThat(output.toString()).isEqualTo(
                "   Month-Year   |  Usage   \n"+
                "----------------+----------\n"+
                "February-2017   |  1250.00\n"+
                "April-2017      |   100.45\n");
    }

    private final Q183272 target = new Q183272();


}