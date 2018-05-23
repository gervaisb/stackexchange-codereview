package cli;

import java.io.File;

/**
 * usage: init QUESTION-ID
 * Initiate the work from a question by creating the package and main class.
 */
public class Init {
    public static void main(String[] args) throws Exception {
        if ( args.length!=1 || !args[0].matches("\\d+")) {
            System.out.println("question id expected, see usage.");
        } else {
            new Question(args[0]).writeTo(new File("./target"));
        }
    }
}
