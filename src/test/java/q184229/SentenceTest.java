package q184229;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class SentenceTest {


    @Test
    public void should_reverse_words() {
        Sentence target = new Sentence("There's a snake in my boot!");

        assertThat(target.reverse().toString())
                .isEqualTo("boot! my in snake a There's");
    }

    @Test
    public void should_not_change_single_word() {
        Sentence target = new Sentence("Hello");

        assertThat(target.reverse().toString())
                .isEqualTo("Hello");
        assertThat(target.reverse()).isSameAs(target);
    }
}