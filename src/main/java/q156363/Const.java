package q156363;

import java.util.function.Function;

class Const implements Function<Integer, Integer> {
    private final Integer value;

    public Const(Integer value) {
        this.value = value;
    }

    @Override
    public Integer apply(Integer integer) {
        return value;
    }

    @Override
    public String toString() {
        return "Const{"+ value + '}';
    }
}
