package q156363;

/**
 *
 */
interface CalculatorView {
    interface Reaction {
        void apply();
    }

    String getText();

    void setText(String text);

    void onClear(Reaction reaction) ;
    void onExecute(Reaction reaction);

    void onAdd(Reaction reaction);
    void onSubstract(Reaction reaction) ;
    void onDivide(Reaction reaction);
    void onMultiply(Reaction reaction);
}
