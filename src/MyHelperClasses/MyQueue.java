package MyHelperClasses;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by user on 15/11/16.
 */

public class MyQueue<E> {
    private Stack<E> s1 = new Stack<E>();
    private Stack<E> s2 = new Stack<E>();
    private Stack<E> stack;

    public void enqueue (E item) {
        s1.push(item);
    }

    public E dequeue () throws Exception {
        //if (s1.isEmpty())   throw new Exception("Queue is empty");
        while (!s1.isEmpty())   s2.push(s1.pop());
        E item = s2.pop();
        while (!s2.isEmpty())   s1.push(s2.pop());
        return item;
    }

    public E peek() throws Exception {
        //if (s1.isEmpty())   throw new Exception("Queue is empty");
        while (!s1.isEmpty())   s2.push(s1.pop());
        E item = s2.pop();
        s1.push(item);
        while (!s2.isEmpty())   s1.push(s2.pop());
        return item;
    }

    // for recursive impl
    public void enQueue (E x) {
        stack.push(x);
    }

    // for recursive impl
    public E deQueue () {
        if (stack.isEmpty())
            throw new NoSuchElementException();
        if (stack.size() == 1)
            return stack.pop();

        E top = stack.pop();
        E bottom = deQueue();
        stack.push(top);

        return bottom;
    }

    public E peek_rec() {
        if (stack.isEmpty())
            throw new NoSuchElementException();
        if (stack.size() == 1)
            return stack.peek();

        E top = stack.pop();
        E bottom = peek_rec();
        stack.push(top);

        return bottom;
    }

    public String toString() {
        return s1.toString();
    }
}