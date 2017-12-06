package q182084;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;


public class Q182084Test {

    private final Stack<Integer> sorted = stackOf(20, 20, 17, 11, 8, 8, 3 ,2);
    private final Stack<Integer> unsorted = stackOf(18, 12, 15, 6, 1);
    private final Stack<Integer> single = stackOf(1);
    private final Stack<Integer> empty = new Stack<>();


    @Test
    public void must_return_true_for_empty_stack() {
        assertTrue(Q182084.isSorted(empty));
    }

    @Test
    public void must_return_true_for_singleton_stack() {
        assertTrue(Q182084.isSorted(single));
    }

    @Test
    public void must_return_true_for_sorted_stack() {
        assertTrue(Q182084.isSorted(sorted));
    }

    @Test
    public void must_return_false_for_unsorted_stack() {
        assertFalse(Q182084.isSorted(unsorted));
    }

    private static <E extends Comparable> Stack<E> stackOf(E... elements) {
        Stack<E> stack = new Stack<>();
        for (E element : elements) {
            stack.push(element);
        }
        return stack;
    }

}