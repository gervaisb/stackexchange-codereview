package q182089;

/*
 * I was asked to make a blood type compatibility GUI app. The code compiles and
 * how I want it to. When a blood type is selected from the combo box a message
 * pops up with all the compatible types. I feel that there could be an easy way
 */
public class Q182089 {
    public static void main(String[] args) {
        new BloodCompatibilityUi(new BloodCompatibilityService());
    }
}
