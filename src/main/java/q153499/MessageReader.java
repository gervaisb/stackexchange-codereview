package q153499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

/**
 * A reader that continually read {@link Message}s from an {@link InputStream}
 * and push them to a {@link BlockingQueue}.
 */
public class MessageReader {
    private final BufferedReader reader;
    private final BlockingQueue queue;

    /**
     * Create an reader that will read messages from {@param reader} and
     * publish them to the {@param queue}.
     *
     * @param input Stream from where messages are read
     * @param queue Blocking queue to publish message
     */
    public MessageReader(InputStream input, BlockingQueue queue) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.queue = queue;
    }

    /**
     * Continually read the stream for new messages. This method is blocking
     * and interrupt when a closure message is received.
     * Abnormal termination are caused by {@link IOException} when reading from
     * the stream or {@link InterruptedException}.
     *
     * @exception InterruptedException when the queue cannot be writed.
     * @exception IOException when reading from the stream.
     * @see Message#isClosure()
     */
    public void run() throws IOException, InterruptedException {
        for (String line = reader.readLine();line != null;line = reader.readLine()) {
            Message message = new Message(line);
            if (message.isClosure()) {
                break;
            } else{
                queue.put(message);
            }
        }
    }
}
