package q165402;

import java.util.Arrays;

class Connection {
    private static final int NAME = 0;
    private static final int CIDR = 1;
    private final String[] source;
    private final String target;

    public Connection(String source, String target) {
        assert source.indexOf('/')>-1 : "wrong source format, 'name/cidr' expected";
        this.source = source.split("/");
        this.target = target;
    }

    public String getSourceName() {
        return source[NAME];
    }

    public int getSourceCidr() {
        return Integer.parseInt(source[CIDR]);
    }

    @Override
    public String toString() {
        return "Connection{" + source[NAME] + '/'+ source[CIDR] + " -> " + target + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Connection that = (Connection) o;
        return  source[NAME].equals(that.source[NAME]) &&
                source[CIDR].equals(that.source[CIDR]) &&
                target.equals(that.target);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(source);
        result = 31 * result + target.hashCode();
        return result;
    }
}
