package q156363;

/**
 *
 */
public class Multiplication extends Operation {

    public Multiplication(Integer left) {
        super(left);
    }

    @Override
    public Integer apply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public String toString() {
        return "Multiplication{" + left + "*x}";
    }
}
