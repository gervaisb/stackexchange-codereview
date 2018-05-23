package q153499;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.atomic.AtomicLong;

public class Message {
    private static AtomicLong SEQUENCE = new AtomicLong(0);
    private static String CLOSURE = "OKBYE";

    private final long counter = SEQUENCE.incrementAndGet();
    private final long time = System.nanoTime();
    private final String line;

    public Message(@NotNull String line) {
        this.line = line;
    }

    public long getSequenceNumber() {
        return counter;
    }

    public String getContent() {
        return line;
    }

    public boolean isClosure() {
        return CLOSURE.equals(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Message message = (Message) o;

        if (counter != message.counter)
            return false;
        if (time != message.time)
            return false;
        return line.equals(message.line);
    }

    @Override
    public int hashCode() {
        int result = (int) (counter ^ (counter >>> 32));
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + line.hashCode();
        return result;
    }


}
