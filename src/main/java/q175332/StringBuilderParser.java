package q175332;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class StringBuilderParser implements Function<String, Map<String, String>> {
    @Override
    public Map<String, String> apply(String s) {
        Map<String, String> maps = new HashMap<>();
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        String key = "";
        String value = "";

        char prev = ' ';
        for (int i = 0;i < length;i++) {
            char a = s.charAt(i);
            if (a != '=' && a != '&' && prev != a) {
                sb.append(a);
            }
            //finds a key
            else if (a == '=' && prev != a && prev != '&') {
                key = sb.toString();
                sb = new StringBuilder();
            }
            // finds a value and also ensuring a key is not empty
            else if (a == '&' && prev != a && prev != '=') {
                value = sb.toString();
                sb = new StringBuilder();
                maps.put(key, value);
            }
            // checks corner case to see if a value is empty
            else if (prev == '=' && a == '&') {
                value = "";
                maps.put(key, value);
                sb = new StringBuilder();
            }
            //how to check if a key is missing and skip to other pair

            prev = a;
        }
        return maps;
    }
}
