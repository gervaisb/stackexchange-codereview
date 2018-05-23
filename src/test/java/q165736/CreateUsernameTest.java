package q165736;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateUsernameTest {

    private static final CreateUsername target = new CreateUsername();

    @Test
    public void generate_with_empty_string_give_nothing() {
        String[] usernameAndEmail = target.generate("");

        assertEquals("", usernameAndEmail[0]);
        assertEquals("", usernameAndEmail[1]);
    }

    @Test
    public void generate_with_one_word_give_nothing() {
        String[] usernameAndEmail = target.generate("Word");

        assertEquals("", usernameAndEmail[0]);
        assertEquals("", usernameAndEmail[1]);
    }

    @Test
    public void generate_with_two_words_give_username_and_email() {
        String[] usernameAndEmail = target.generate("First Second");

        assertEquals("FirstS", usernameAndEmail[0]);
        assertEquals("First.Second@test.com", usernameAndEmail[1]);
    }

    @Test
    public void generate_with_three_words_give_username_and_email() {
        String[] usernameAndEmail = target.generate("First Second Third");

        assertEquals("First", usernameAndEmail[0]);
        assertEquals("First.Third@test.com", usernameAndEmail[1]);
    }

    @Test
    public void generate_with_four_words_give_nothing() {
        String[] usernameAndEmail = target.generate("First Second Third Fourth");

        assertEquals("", usernameAndEmail[0]);
        assertEquals("", usernameAndEmail[1]);
    }


}
