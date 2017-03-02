package q156363;

/**
 *
 */
public class Substraction extends Operation {

    public Substraction(Integer left) {
        super(left);
    }

    @Override
    public Integer apply(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public String toString() {
        return "Substraction{" + left + "-x}";
    }
}
