package q175332;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class PairsDecoder implements Function<String, Map<String, String>>{
    @Override
    public Map<String, String> apply(String input) {
        Map<String, String> retVal = new HashMap<>();
        int fromIndex = 0;
        int toIndex = 0;
        while (toIndex != -1)
        {
            String key = "";
            String value = "";
            toIndex = input.indexOf('=', fromIndex);
            if (toIndex - fromIndex > 1)
            {
                key = input.substring(fromIndex, toIndex);
                fromIndex = toIndex + 1;
                toIndex = input.indexOf('&', fromIndex);
                if (toIndex == -1)
                {
                    value = input.substring(fromIndex, input.length());
                } else
                {
                    value = input.substring(fromIndex, toIndex);
                }
                retVal.put(key, value);
                fromIndex = toIndex + 1;
            } else
            {
                fromIndex = input.indexOf('&', toIndex) + 1;
            }
        }
        return retVal;
    }
}
