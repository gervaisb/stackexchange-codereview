package q165402;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Generator {

    private final ConnectionParser parser;
    private final Protocol protocol;

    public Generator(final ConnectionParser parser, final Protocol protocol) {
        this.protocol = protocol;
        this.parser = parser;
    }

    public void writeTo(OutputStream out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        protocol.prepare(writer);
        while ( parser.hasNext() ) {
            protocol.write(parser.next(), writer);
        }
        protocol.finish(writer);
        writer.flush();
    }
}
