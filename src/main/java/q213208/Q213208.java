package q213208;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This program prints out a table in the console. Suggestions for improvements are welcome.
 * <p>
 * Example output
 * <pre>
 * Name             Sex      Age
 * Klaus Ulbrecht   male     12
 * Dieter           male     14
 * Ursula           female   16</pre>
 */
public class Q213208 {

    private static List<List<String>> table;

    public static void main(String[] args) throws IOException {
        initTable();
        int spacing = 3;
        System.out.println("> Original");
        printTable(spacing);

        Ascii console = new Ascii(System.out, "   ");

        Table tbl = new Table(
                new String[]{"Name", "Sex", "Age"},
                new Object[][]{
                        {"Klaus Ulbrecht", "male", 12},
                        {"Dieter", "male", 14},
                        {"Ursula", "female", 16}
                });
        System.out.println("> Mine");
        tbl.write(console);

        System.out.println("> Mine : sorted(age)");
        tbl.sort(2, String::compareToIgnoreCase)
                .write(console);

        System.out.println("> Mine : filter(male)");
        tbl.filter(1, "male"::equalsIgnoreCase)
                .write(console);
    }

    private static void initTable() {
        List<String> row1 = Arrays.asList("Name", "Klaus Ulbrecht", "Dieter", "Ursula");
        List<String> row2 = Arrays.asList("Sex", "male", "male", "female");
        List<String> row3 = Arrays.asList("Age", "12", "14", "16");
        table = Arrays.asList(row1, row2, row3);
    }

    private static void printTable(int spacing) {
        List<Integer> maxLengths = findMaxLengths();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.get(0).size(); i++) {
            for (int j = 0; j < table.size(); j++) {
                String currentValue = table.get(j).get(i);
                sb.append(currentValue);
                for (int k = 0; k < (maxLengths.get(j) - currentValue.length() + spacing); k++) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static List<Integer> findMaxLengths() {
        List<Integer> maxLengths = new ArrayList<>();
        for (List<String> row : table) {
            int maxLength = 0;
            for (String value : row) {
                if (value.length() > maxLength) {
                    maxLength = value.length();
                }
            }
            maxLengths.add(maxLength);
        }
        return maxLengths;
    }
}
