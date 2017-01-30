package q153499;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * I would like someone to look at my class for reading messages from a
 * network socket. In short, the whole app should behave like a regular ping
 * command with some additional functionalities. The point here is that I want
 * to separate the reading of messages, and processing (which is done each
 * second). This object is run in a separate thread, and the main thread is
 * responsibe for processing the messages. After each second the main thread is
 * notified to process collected messages. My motivation for this approach
 * (separating the work for reading and processing) is to remove the overload
 * of message processing from the readout, since each message gets a time stamp
 * immediately after it is read, which is used later for calculating RTT. Is
 * this a good approach? If so, could the reading of messages be somehow
 * improved? Or this is good enough?
 */
public class Q153499 {
    public static void main(String[] args) {
        System.out.println(
            "Read messages from command line and print them on the console each seconds.\n" +
            "Send the `OKBYE` message to exit the system.                               \n");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(5, true);
        MessageProcessor processor = new MessageProcessor(queue);
        MessageReader reader = new MessageReader(System.in, queue);
        try {
            executor.scheduleWithFixedDelay(processor, 1, 1, TimeUnit.SECONDS);
            reader.run();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            executor.shutdown();
        }
    }
}
