package q156363;

class Addition extends Operation {

    public Addition(Integer left) {
        super(left);
    }

    @Override
    public Integer apply(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public String toString() {
        return "Addition{" + left + "+x}";
    }
}
