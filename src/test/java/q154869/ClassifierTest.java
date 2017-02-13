package q154869;

import org.junit.Test;

import static org.junit.Assert.*;


public class ClassifierTest {

    @Test
    public void should_parse_any_token() {
        Classifier classifier = new Classifier("Hello,2,!");
        assertEquals("Hello", 1, classifier.getNumberOf("Hello"));
        assertEquals("2", 1, classifier.getNumberOf("2"));
        assertEquals("!", 1, classifier.getNumberOf("!"));
    }

    @Test
    public void should_count_tokens() {
        Classifier classifier = new Classifier("a,b,a");
        assertEquals("a", 2, classifier.getNumberOf("a"));
        assertEquals("b", 1, classifier.getNumberOf("b"));
    }
    
    @Test
    public void should_default_to_zero() {
        Classifier classifier = new Classifier("");
        assertEquals("a", 0, classifier.getNumberOf("a"));
    }

}
