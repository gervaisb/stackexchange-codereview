package q165402;

import org.junit.Test;
import q165402.Connection;
import q165402.ConnectionParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class ConnectionParserTest {

    private final ConnectionParser target = new ConnectionParser(new FileInputStream("src/test/resources/input.txt"));


    @Test
    public void should_parse_connections() {
        assertEquals(target.next(), new Connection("192.168.1.0/30", "FastEthernet0/0"));
        assertEquals(target.next(), new Connection("192.168.1.8/30", "FastEthernet0/1"));
        assertEquals(target.next(), new Connection("192.168.2.0/24", "Ethernet0/3/0"));
    }



    public ConnectionParserTest() throws FileNotFoundException { /**/ }
}
