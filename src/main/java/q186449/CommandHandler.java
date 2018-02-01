package q186449;

import java.lang.reflect.InvocationTargetException;
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
        return line.substring(0, line.indexOf(' '));
    }

    private String[] readParameters(String line) {
        String parameters = line.substring(line.indexOf(' ')+1, line.length());
        return parameters.split( " ");
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
