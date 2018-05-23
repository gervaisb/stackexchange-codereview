package q165402;

import org.junit.Assert;
import org.junit.Test;
import q165402.ConnectionParser;
import q165402.Generator;
import q165402.Ospfv2Protocol;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GeneratorIT {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final ConnectionParser parser = new ConnectionParser(new FileInputStream("src/test/resources/input.txt"));

    @Test
    public void writeTo_ospf() throws Exception {
        Generator target = new Generator(parser, new Ospfv2Protocol());
        target.writeTo(output);

        assertEquals("output.ospf", output);
    }

     // ~ ---------------------------------------------------------------- ~ //

    public GeneratorIT() throws FileNotFoundException { /**/ }

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
