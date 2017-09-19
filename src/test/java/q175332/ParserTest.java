package q175332;

import org.junit.Test;

import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class ParserTest {

    private final String input = "key1={value1}&key2&key3={value3}";

    @Test
    public void RegexParser_test() throws Exception {
        verify(new RegexParser());
    }

    @Test
    public void SplitParser_test() throws Exception {
        verify(new SplitParser());
    }

    @Test
    public void StringBuilderParser_test() throws Exception {
        verify(new StringBuilderParser());
    }

    @Test
    public void QueryStringDecoder_test() throws Exception {
        verify(new QueryStringDecoder());
    }

    @Test
    public void PairsDecoder_test() throws Exception {
        verify(new PairsDecoder());
    }

    private void verify(Function<String, Map<String, String>> parser) {
        assertThat(parser.apply(input))
                .containsEntry("key1", "{value1}")
                .containsEntry("key3", "{value3}")
                .containsKey("key2");
    }
}
