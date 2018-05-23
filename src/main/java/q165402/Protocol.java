package q165402;

import java.io.PrintWriter;

public abstract class Protocol {
    public void prepare(PrintWriter out) {
        out.println("enable");
        out.println("conf t");
        out.println("router ospf 1");
    }

    public abstract void write(Connection connection, PrintWriter out);

    public void finish(PrintWriter out) {
        out.println("end");
    }
}
