package q183272;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConsumptionsReader implements AutoCloseable, Iterable<Consumption> {

    private final static Pattern PATTERN = Pattern.compile("\\(\"(\\d{4}-\\d{2}-\\d{2})\", (\\d+\\.?\\d*)\\)");
    private final BufferedReader reader;

    private Matcher matcher = null;

    public ConsumptionsReader(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public void close() throws Exception {
        reader.close();
        matcher = null;
    }

    @Override
    public Iterator<Consumption> iterator() {
        return new Iterator<Consumption>() {
            Consumption current, next = parseNext();

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Consumption next() {
                if (next != null) {
                    current = next;
                    next = parseNext();
                    return current;
                } else {
                    return null;
                }
            }
        };
    }

    private Consumption parseNext() throws NoSuchElementException {
        try {
            if (matcher != null && matcher.find()) {
                return Consumption.of(matcher.group(1), Double.parseDouble(matcher.group(2)));
            } else if (readNext() != null) {
                return parseNext();
            } else {
                return null;
            }
        } catch (IOException ioe) {
            // If we cannot read more we assume there is no more elements
            throw new NoSuchElementException("Cannot read more consumptions : "+ioe.getMessage());
        }
    }

    private Matcher readNext() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            matcher = PATTERN.matcher(line);
        } else {
            matcher = null;
        }
        return matcher;
    }
}
