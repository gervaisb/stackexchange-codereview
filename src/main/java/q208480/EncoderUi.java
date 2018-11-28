package q208480;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class EncoderUi {

    private static final Scanner sc = new Scanner(System.in);

    private boolean stop = false;
    private static boolean readyToTranslate = false;

    private static String userInput;
    private static String messageHead;

    private final Cipher cipher = new Cipher();


    public static void main (String[] args) {
        EncoderUi ui = new EncoderUi();
        ui.start();
    }

    private void start() {
        do {
            Consumer<EncoderUi> action = askForAction();
            action.accept(this);
        } while (!stop);
    }

    private void print(String string) {
        System.out.print(string);
    }

    private Consumer<EncoderUi> askForAction(){
        System.out.println("\n\nType \"encrypt(message_to_encrypt)\" or \"decrypt(message_to_decrypt)\" please (or \"stop\" to stop).");
        final String input = Normalizer.normalize(sc.nextLine(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();

        if ( "stop".equals(input) ) {
            return ui -> ui.stop = true;
        } else if ( input.startsWith("encrypt(") ) {
            return translate(input, cipher::encode);
        } else if ( input.startsWith("decrypt(") ) {
            return translate(input, cipher::decode);
        } else {
            return ui -> ui.print("Invalid action \""+input+"\".");
        }

    }

    private Consumer<EncoderUi> translate(String input, Function<String, String> algorithm) {
        return ui -> {
            String message = parse(input);
            String result = algorithm.apply(message);
            ui.print(result);
        };
    }

    private String parse(String input) {
        return input
                .substring(0, input.length()-1)
                .substring(input.indexOf('(')+1);
    }

}