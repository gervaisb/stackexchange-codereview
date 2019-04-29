package q156363;

import java.util.Stack;
import java.util.function.Function;

/**
 * A accumulation of operations that are stacked until the closing number to be executed.
 */
class Equation {

    private final Stack<Function<Integer, Integer>> operations = new Stack<>();

    public void push(Function<Integer, Integer> operation) {
        operations.push(operation);
    }

    public Integer resolve(final Integer x) {
        Integer right = x;
        while ( !operations.isEmpty() ) {
            right = operations.pop().apply(right);
        }
        return right;
    }

    @Override
    public String toString() {
        return "Equation{" + operations + '}';
    }
}
