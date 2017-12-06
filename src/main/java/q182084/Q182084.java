package q182084;

import java.util.Iterator;
import java.util.Stack;

class Q182084 {

    public static void main(String[] args) {

    }

    public static <E extends Comparable<E>> boolean isSorted(Stack<E> stack) {
        return isSorted(stack.iterator());
    }

    public static <E extends Comparable<E>> boolean isSorted(Iterator<E> it) {
        if ( !it.hasNext() ) {
            return true;
        }
        E head = it.next();
        E next;
        while ( it.hasNext() ) {
            next = it.next();
            if ( head.compareTo(next)<0 ) {
                return false;
            }
            head = next;
        }
        return true;
    }
}
