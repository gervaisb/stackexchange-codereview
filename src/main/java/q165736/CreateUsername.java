package q165736;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateUsername {
    public static void main(String[] args) throws IOException {
        CreateUsername generator = new CreateUsername();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide Name");

        String[] usernameAndEmail = generator.generate(input.readLine());
        System.out.println("Windows Username is : " + usernameAndEmail[0]);
        System.out.println("Email ID is : " + usernameAndEmail[1]);

    }

    public String[] generate(String input) {
        int counter = getWordsCount(input);
        System.out.println("words in the string are : " + counter);
        return getUserNameAndEmail(input, counter);
    }

    private String[] getUserNameAndEmail(String input, int counter) {
        //Split the names and assign the Windows Username
        String firstName = "";
        String firstLetterSurname = "";
        String surname = "";
        String windowsUsername = "";
        String emailID = "";
        switch (counter) {
        case 2:
            System.out.println("Inside for count 2");
            firstName = input.split(" ")[0];
            surname = input.split(" ")[1];
            firstLetterSurname = String.valueOf(surname.charAt(0));
            windowsUsername = firstName + "" + firstLetterSurname.toUpperCase();
            emailID = firstName + "." + surname + "@test.com";
            break;
        case 3:
            firstName = input.split(" ")[0];
            surname = input.split(" ")[2];
            windowsUsername = firstName + "" + firstLetterSurname.toUpperCase();
            emailID = firstName + "." + surname + "@test.com";
            break;
        default:
            // Nothing
        }
        return new String[] { windowsUsername, emailID };
    }

    private int getWordsCount(String input) {
        char[] arr = input.toCharArray();
        int i = 0;
        boolean notCounted = true;
        int counter = 0;
        while (i < arr.length) {
            if (arr[i] != ' ') {
                if (notCounted) {
                    notCounted = false;
                    counter++;
                }
            } else{
                notCounted = true;
            }
            i++;
        }
        return counter;
    }
}
