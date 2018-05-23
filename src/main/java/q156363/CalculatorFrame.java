package q156363;

//this is the calculation

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
First of all, use clear names for your classes and fields, and apply remarks from @Martin Spamer. Then are other
remarks about responsibilities and design. In your code you mix UI and logic in one class, this is bad or many reasons
like maintenance and evolutions but also for testing (Search fro Single Responsibility Principle). So what can you do ?
 * *UI*
First, regarding the UI side of your calculator. We can observe in the new (web) frameworks that the trends is for
components. So you may consider a panel for the numbers and another for the operations. Two are better than one
because their roles are differents.
 * For the operations, you can reduce the duplication with one method to create the buttons. And the same for the numbers
where you can also use a for loop to add buttons from 1 to 9..
 *
To simplify the testing, you may consider the MVP pattern. You have a `CalculatorView` interface that contains methods
to get and set the text and to listen to actions. A `CalculatorPresenter` receive this view as constructor parameter
and bind itself to the view in order to react to all events and drive the business.
 *      class CalculatorPresenter {
         private final ClaculatorView view;
 *          CalculatorPresenter(CalculatorView view) {
             this.view = view;
             this.view.onClear(()->{
                this.view.setText("");
             });
             this.view.onAdd(()->{
                // ..
                this.view.setText(result);
             });
         }
     }
 * For Swing, I like this pattern more than MVC because I can test the presenter logic with a mock of the view
 *
*Logic*
For the logic of your app, you may consider another approach with less states and better separation (easier testing
again). In fact, your calculator can be seen as a suit of operations until you request the result (press "=").
 *     abstract class Operation {
        Operation(Integer left) {
            this.left = left;
        }
        abstract Integer apply(Integer right)
    }
 *     class Addition extends Operation {
        Integer apply(Integer right) {
            return this.left + right;
        }
    }
 * Doing so you can easily test all of your operations individually. And from the UI, your presenter has to create
and push to correct operation when the user press one operation button. Your `Equation` is a stack of `Operation`
that are waiting for the rightmost number to resolve. You just have to read it when the user press the "=" button.
 *     class Equation {
        Stack<Operation> operations = new Stack<>;
        void push(Operation operation) {
           this.operations.push(operation);
        }
 *         Integer resolve(Integer x) {
             Integer right = x;
             while ( !operations.isEmpty() ) {
                 right = operations.pop().apply(right);
             }
             return right;
        }
    }
 * And that's all, to reset you just have to create a new `Equation` from your presenter. And as bonus you solve your
"1 - 2 = 1" bug because you reduce from the right.
 */
class CalculatorFrame extends JFrame implements CalculatorView {


    private JTextField output = new JTextField(20);
    private Reaction onClear = null;
    private Reaction onAdd = null;
    private Reaction onSubstract = null;
    private Reaction onDivide = null;
    private Reaction onMultiply = null;
    private Reaction onExecute = null;


    public CalculatorFrame() {
        super("Calc");
        setLayout(new FlowLayout());
        add(output);
        add(new Numbers());
        add(new Operations());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 200);
    }

    @Override
    public String getText() {
        return output.getText();
    }

    @Override
    public void setText(String text) {
        output.setText(text);
    }

    @Override
    public void onClear(Reaction reaction) {
        this.onClear = reaction;
    }

    @Override
    public void onAdd(Reaction reaction) {
        this.onAdd = reaction;
    }

    @Override
    public void onSubstract(Reaction reaction) {
        this.onSubstract = reaction;
    }

    @Override
    public void onDivide(Reaction reaction) {
        this.onDivide = reaction;
    }

    @Override
    public void onMultiply(Reaction reaction) {
        this.onMultiply = reaction;
    }

    @Override
    public void onExecute(Reaction reaction) {
        this.onExecute = reaction;
    }

    private final class Operations extends JPanel {
        public Operations() {
            super(new GridLayout(3, 2), true);
            addButtonFor("C", ()->onClear.apply());
            addButtonFor("+", ()->onAdd.apply());
            addButtonFor("-", ()->onSubstract.apply());
            addButtonFor("*", ()->onMultiply.apply());
            addButtonFor("/", ()->onDivide.apply());
            addButtonFor("=", ()->onExecute.apply());
        }

        private void addButtonFor(String action, Reaction reaction) {
            add(new JButton(new AbstractAction(action) {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    reaction.apply();
                }
            }));
        }
    }

    private final class Numbers extends JPanel {
        public Numbers() {
            super(new GridLayout(4, 3), true);
            for (int i = 1;i < 10;i++) {
                addButtonFor(i);
            }
            addButtonFor(0);
        }
        private void addButtonFor(int number) {
            add(new JButton(new AbstractAction(Integer.toString(number)) {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    output.setText(String.format("%1$s%2$d", output.getText(), number));
                }
            }));
        }
    }

}
