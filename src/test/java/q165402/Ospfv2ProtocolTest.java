package q165402;

import org.junit.Test;
import q165402.Connection;
import q165402.Ospfv2Protocol;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class Ospfv2ProtocolTest {

    private final Ospfv2Protocol target = new Ospfv2Protocol();
    private final StringWriter writer = new StringWriter();

    @Test
    public void format_connections() {
        target.write(new Connection("192.168.1.0/30", "FastEthernet0/0"),
                new PrintWriter(writer));

        assertEquals("network 192.168.1.0 0.0.0.3 area 51\n", writer.toString());
    }
}
