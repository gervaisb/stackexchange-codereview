package q165402;

import java.io.PrintWriter;

public class Ospfv2Protocol extends Protocol {
    @Override
    public void write(Connection connection, PrintWriter out) {
        out.append("network ")
                .append(connection.getSourceName())
                .append(' ')
                .append(getMask(connection.getSourceCidr()))
                .append(' ')
                .append("area 51")
                .println();
    }

    private String getMask(final int cidr) {
        switch (cidr) {
        case 30:
            return "0.0.0.3";
        case 24:
            return "0.0.0.255";
        default:
            return "0.0.0.0";
        }
    }
}
