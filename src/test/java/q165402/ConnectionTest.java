package q165402;

import org.junit.Test;
import q165402.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConnectionTest {

    private final Connection fromAtoB = new Connection("127.0.0.1/30", "Other/1");
    private final Connection fromBtoA = new Connection("Other/1", "127.0.0.1/30");

    @Test
    public void are_equals_when_same_source_and_target() {
        assertEquals(fromAtoB, new Connection("127.0.0.1/30", "Other/1"));
        assertNotEquals(fromAtoB, fromBtoA);
    }

}
