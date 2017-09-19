package q175332;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class SplitParser implements Function<String, Map<String, String>> {
    @Override
    public Map<String, String> apply(String s) {
        // String s represents ParamAndMacro
        String[] split = s.split("=|\\&");
        int length = split.length;
        Map<String, String> maps = new HashMap<>();
        for (int i = 0;i < length;i += 2) {
            maps.put(split[i], split[i + 1]);
        }
        return maps;
    }
}
