package q209688;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Blaise Gervais
 */
class Text {

    private final Set<String> words;
    private final Set<String> blacklist;

    public Text(String content, Set<String> blacklist) {
        this.words = new HashSet<>(Arrays.asList(content.split("\\s")));
        this.blacklist = blacklist;
    }

    public boolean isSafe() {
        for (String forbidden: this.blacklist) {
            if (this.words.contains(forbidden) ) {
                return false;
            }
        }
        return true;
    }
}
