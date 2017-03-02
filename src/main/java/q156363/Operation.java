package q156363;

import java.util.function.Function;


abstract class Operation implements Function<Integer, Integer> {

    protected final Integer left;

    protected Operation(Integer left) {
        this.left = left;
    }

    abstract Integer apply(Integer left, Integer right);

    @Override
    public Integer apply(Integer right) {
        return apply(left, right);
    }

}
