package q153499;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * A message processor process all messages present in a {@link BlockingQueue}.
 *
 * <b>Dummy implementation</b> that just print the message sequence number and
 * content the the system output.
 */
public class MessageProcessor implements Runnable {

    private final BlockingQueue<Message> queue;

    /**
     * Create a processor that print all messages from the {@param queue} to the
     * system output.
     *
     * @param queue Blocking queue to read messages
     */
    public MessageProcessor(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    /**
     * Read all messages from the queue and print them to {@link System#out}
     */
    public void run() {
        for (Message message : allIn(queue)) {
            process(message);
        }
    }

    private void process(Message message) {
        System.out.printf("#%1$04d : %2$s%n", message.getSequenceNumber(), message.getContent());
    }

    private List<Message> allIn(BlockingQueue<Message> queue) {
        List<Message> elements = new ArrayList<Message>(10);
        queue.drainTo(elements);
        return elements;
    }
}
