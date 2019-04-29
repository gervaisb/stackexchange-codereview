package q209688;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Blaise Gervais
 */
public class TextTest {
    private final Set<String> blacklist = Collections.singleton("stupid");

    @Test
    public void must_be_safe_when_there_is_no_blacklisted_words() {
        assertThat(new Text("Hello world", this.blacklist).isSafe())
                .isTrue();
    }

    @Test
    public void must_be_unsafe_when_there_is_blacklisted_words() {
        assertThat(new Text("Hello stupid world", this.blacklist).isSafe())
                .isFalse();
    }

    @Test
    public void must_be_unsafe_based_on_Word_boundary() {
        assertThat(new Text("stupid world", this.blacklist).isSafe())
                .isFalse();

        assertThat(new Text("stupidworld", this.blacklist).isSafe())
                .isTrue();
    }

}
