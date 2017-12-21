package q183272;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.io.StringReader;


public class ConsumptionsReaderTest {

    private final ConsumptionsReader target = new ConsumptionsReader(new StringReader(
            "((\"2017-02-13\", 1200.00), (\"2017-02-19\", 50.00),\n (\"2017-04-10\", 100.45))"
    ));

    @Test
    public void must_read_all_consumptions() {
        assertThat(target.iterator()).contains(
                Consumption.of("2017-02-13", 1200.00),
                Consumption.of("2017-02-19", 50.00),
                Consumption.of("2017-04-10", 100.45)
        );
    }
}