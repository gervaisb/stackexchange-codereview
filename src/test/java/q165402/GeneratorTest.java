package q165402;

import org.junit.Assert;
import org.junit.Test;
import q165402.Connection;
import q165402.ConnectionParser;
import q165402.Generator;
import q165402.Protocol;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeneratorTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final ConnectionParser parser = mock(ConnectionParser.class);
    private final Protocol protocol = mock(Protocol.class);
    private final Generator target = new Generator(parser, protocol);
    private final Connection fromAtoB = new Connection("127.0.0.1/30", "Other/10");
    private final Connection fromBtoC = new Connection("127.0.0.2/20", "Self/20");

    @Test
    public void invoke_protocol_for_each_connection() throws Exception {
        when(parser.hasNext()).thenReturn(true, true, false); // 2 connections
        when(parser.next()).thenReturn(fromAtoB, fromBtoC);

        target.writeTo(output);

        verify(protocol).prepare(any(PrintWriter.class));
        verify(parser, times(3)).hasNext();
        verify(parser, times(2)).next();
        verify(protocol).write(eq(fromAtoB), any(PrintWriter.class));
        verify(protocol).write(eq(fromBtoC), any(PrintWriter.class));
        verify(protocol).finish(any(PrintWriter.class));
    }




    public GeneratorTest() throws FileNotFoundException { /**/ }

    private static void assertEquals(String file, ByteArrayOutputStream actual) throws IOException {
        ByteArrayOutputStream expected = new ByteArrayOutputStream(actual.size());
        try (FileInputStream in = new FileInputStream("src/test/resources/"+file+".txt")) {
            byte[] buffer = new byte[256];
            for (int reads = in.read(buffer); reads>-1; reads = in.read(buffer)) {
                expected.write(buffer, 0, reads);
            }
            Assert.assertArrayEquals(expected.toByteArray(), actual.toByteArray());
        }
    }

}
