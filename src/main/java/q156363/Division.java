package q156363;

/**
 *
 */
public class Division extends Operation {

    public Division(Integer left) {
        super(left);
    }

    @Override
    public Integer apply(Integer left, Integer right) {
        return left / right;
    }

    @Override
    public String toString() {
        return "Division{" + left + "/x}";
    }
}
