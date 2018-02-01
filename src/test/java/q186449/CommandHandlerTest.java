package q186449;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CommandHandlerTest {

    private final SystemSpy spy = new SystemSpy();
    private final CommandHandler target = new CommandHandler(spy);


    @Test
    public void should_convert_command_name_to_camelCase() {
        assertThat(CommandHandler.convertToCamelCase("hello"))
                .isEqualTo("hello");

        assertThat(CommandHandler.convertToCamelCase("hello-world"))
            .isEqualTo("helloWorld");

        assertThat(CommandHandler.convertToCamelCase("hello-big-world"))
                .isEqualTo("helloBigWorld");
    }

    @Test
    public void should_execute_command_method() {
        target.handle("command-one arg1 arg2");
        assertThat(spy.commandOneExecuted).isTrue();
    }


    @Test
    public void should_execute_command_with_parameters() {
        target.handle("command-one arg1 arg2");
        assertThat(spy.commandOneExecuted).isTrue();
        assertThat(spy.commandOneParams).containsExactly("arg1", "arg2");
    }

    private final class SystemSpy {
        private boolean commandOneExecuted = false;
        private String[] commandOneParams = null;
        public void commandOne(String[] parameters) {
            commandOneExecuted = true;
            commandOneParams = parameters; // Bad
        }
    }

}