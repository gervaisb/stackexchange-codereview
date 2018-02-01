package q186449;

import java.lang.reflect.Method;

class CommandHandler {

    private final Object system;

    public CommandHandler(Object system) {
        this.system = system;
    }

    public void handle(String line) {
        String command = readCommand(line);
        String[] parameters = readParameters(line);
        try {
            Method method = findHandlerMethod(command);
            method.invoke(system, new Object[]{parameters});
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); // Command not found
        } catch (Exception e) {
            e.printStackTrace(); // Command execution failed
        }
    }

    private String readCommand(String line) {
        final int space = line.indexOf(' ');
        return space>-1
                ?line.substring(0, space)
                :line;
    }

    private String[] readParameters(String line) {
        final int space = line.indexOf(' ');
        return space>-1
                ?line.substring(space+1, line.length()).split(" ")
                :new String[0];
    }

    private Method findHandlerMethod(String command) throws NoSuchMethodException {
        String methodName = convertToCamelCase(command);
        return system.getClass().getMethod(methodName, String[].class);
    }

    static String convertToCamelCase(String name) {
        StringBuilder camelCase = new StringBuilder(name);
        for (int current=0, next=1; next<camelCase.length(); current++, next++) {
            if ( camelCase.charAt(current)=='-') {
                camelCase.setCharAt(next, Character.toUpperCase(camelCase.charAt(next)));
                camelCase.deleteCharAt(current);
            }
        }
        return camelCase.toString();
    }
}
