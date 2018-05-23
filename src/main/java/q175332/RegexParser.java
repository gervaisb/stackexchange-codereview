package q175332;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class RegexParser implements Function<String, Map<String, String>>  {
    private final Pattern pattern = Pattern.compile("(\\w+)=?([^&]+)?");

    public Map<String, String> apply(String query) {
        Matcher matcher = pattern.matcher(query);
        Map<String, String> map = new HashMap<>();
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;
    }
}
