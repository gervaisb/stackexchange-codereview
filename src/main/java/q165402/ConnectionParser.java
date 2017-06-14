package q165402;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConnectionParser implements Iterator<Connection> {
    private static final Pattern LINE = Pattern.compile("C\\s+(?<source>(?<ip>[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})/(?<port>[0-9]+))(?:.*),\\s+(?<target>.*)");

    private final Iterator<Connection> iterator;

    public ConnectionParser(InputStream in) {
        iterator = new BufferedReader(new InputStreamReader(in)).lines().map(LINE::matcher)
                .filter(Matcher::matches)
                .map(this::parse)
                .iterator();
    }

    private Connection parse(Matcher matcher) {
        return new Connection(matcher.group("source"), matcher.group("target"));
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Connection next() {
        return iterator.next();
    }
}
