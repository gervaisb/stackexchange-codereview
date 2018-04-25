package q192870;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.toSet;

class Violations {
    class Violation {
        final String message;
        final Object field;

        Violation(Object field, String message) {
            this.message = message;
            this.field = field;
        }
    }

    private final Set<Violation> violations = new HashSet<>();

    public void reject(Object field, String message) {
        violations.add(new Violation(field, message));
    }

    public boolean isEmpty() {
        return violations.isEmpty();
    }

    public Set<String> getMessagesFor(Object field) {
        return violations.stream().filter(v -> field.equals(v.field))
                .map(v -> v.message)
                .collect(toSet());
    }

    public void forEach(BiConsumer<Object, Set<String>> consumer) {
        violations.stream().map(v -> v.field).distinct()
                .forEach(f -> consumer.accept(f, getMessagesFor(f)));
    }
}
