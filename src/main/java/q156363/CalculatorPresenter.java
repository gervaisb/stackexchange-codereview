package q156363;

class CalculatorPresenter {

    private final CalculatorView view;
    private Equation equation;

    public CalculatorPresenter(CalculatorView view) {
        this.equation = new Equation();
        this.view = view;
        this.view.onClear(()->{
            equation = new Equation();
            erase();
        });
        this.view.onAdd(()->{
            equation.push(new Addition(readAndErase()));
        });
        this.view.onDivide(()->{
            equation.push(new Division(readAndErase()));
        });
        this.view.onSubstract(()->{
            equation.push(new Substraction(readAndErase()));
        });
        this.view.onMultiply(()->{
            equation.push(new Multiplication(readAndErase()));
        });
        this.view.onExecute(()->{
            Integer result = equation.resolve(readAndErase());
            view.setText(result.toString());
        });
    }

    private Integer readAndErase() {
        Integer value = Integer.valueOf(view.getText());
        erase();
        return value;
    }

    private void erase() {
        view.setText("");
    }

  }
