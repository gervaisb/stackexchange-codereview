package q156363;

/*
First of all, use clear names for your classes and fields, and apply remarks
from @martin-spamer.

Aside of those "styles" remarks, there are others about responsibilities and
design because your code mix UI and logic in one class, this is bad for many
reasons like maintenance and evolutions but also for testing (Search for
_Single Responsibility Principle_). So what can you do ?


Regarding the **UI** of your calculator there are some remarks to clena it.
We can observe in the new (web) frameworks that the trends is for _components_.
You may consider a "componentization" of your app with a panel for the numbers
and another for the operations. Two are better than one because their roles are
differents.

For the `OperationsPanel`, you can reduce the duplication with one _factory
method_ to create the button. A _factory method_ can also be used in the
`NumbersPanel` where you can use a for loop to add the buttons from 1 to 9.

For Swing, I like the _MVP_ pattern more than MVC because I can test the
presenter logic with a mock of the view. With this pattern, you'll have a
`CalculatorView` interface that contains methods to get and set the text and to
listen to operations ('C', '+', '-', ..). A `CalculatorPresenter` receive this
view as constructor parameter and bind itself to it in order to react to all
events and drive the business.


    class CalculatorPresenter {
      private final ClaculatorView view;

      CalculatorPresenter(CalculatorView view) {
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


For the **logic** of your app, you may consider another approach with less
states and better separation (easier testing again). In fact, your calculator
can be seen as a suit of operations until you request the result (press "=").

    abstract class Operation {
      Operation(Integer left) {
        this.left = left;
      }
      abstract Integer apply(Integer right)
    }

    class Addition extends Operation {
      Integer apply(Integer right) {
        return this.left + right;
      }
    }

Doing so you can easily test your operations individually. And from the UI, your
presenter has to create the corresponding operation when the user press one
operation button.

Your `Equation` is a stack of `Operation` that are waiting for the rightmost
number to resolve. You just have to read it when the user press the "=" button
and resolve your equation.

    class Equation {
      Stack<Operation> operations = new Stack<>;
      void push(Operation operation) {
        this.operations.push(operation);
      }
      Integer resolve(Integer x) {
        Integer right = x;
        while ( !operations.isEmpty() ) {
          right = operations.pop().apply(right);
        }
        return right;
      }
    }

And that's all. To reset you just have to create a new `Equation` from your
presenter. (as bonus it solve your `1 - 2 = 1` bug)
 */
public class Q156363 {
    public static void main(String[] args) {
        CalculatorFrame view = new CalculatorFrame();
        new CalculatorPresenter(view);
        view.setVisible(true);
    }
}
