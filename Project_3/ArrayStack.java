package Project_3;

import java.util.Iterator;
import java.lang.IllegalStateException;

public class ArrayStack<E> extends AbstractStack<E> {

    private E[] myStack;
    private int t = -1;

    /**
     * Creates new Array type E instance with the given capacity 
     * @param max The capacity of the new Array being created
     * @throws IllegalArgumentException
     */
    public ArrayStack(int max) throws IllegalArgumentException {
        super(max);
        myStack = (E[]) new Object[max];
    };

    /**
     * Implements the Iterator object to iterate through each element in the stack
     */
    public Iterator<E> iterator() {
        return new ArrayIterator<E>(myStack);
    }

    /**
     * Defines the interface methods used when iterator() is used to iterate through the stack
     */
    private class ArrayIterator<E> implements Iterator<E> {
        int current = -1;
        E[] myStack;

        public ArrayIterator(E[] myStack) {
            this.myStack = myStack;
        }

        public boolean hasNext() {
            if (current < t) {
                return true;
            }
            return false;
        }
        public E next() {
            return myStack[++current];
        }
    }

    /**
	 * Pushes the specified element onto this stack.
	 * @param element the element to be pushed
	 * @throws NullPointerException if {@code element == null}
	 * @throws IllegalStateException if this stack is full
	 */
    public void push(E element) {
        if (isFull()) {
            throw new IllegalStateException();
        } else if (element == null) {
            throw new NullPointerException();
        } else {
            t += 1;
            myStack[t] = element;
        }
    };

    /**
	 * Removes and returns the element at the top of this stack.
	 * @return the element at the top of this stack
	 * @throws IllegalStateException if this stack is empty
	 */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        } else {
            E temp = myStack[t];
            myStack[t] = null;
            t = t - 1;
            return temp;
        }
    };

    /**
	 * Returns the depth of this stack.
	 * Depth is the number of actual elements stored in the stack.
	 * @return the depth of this stack
	 */
    public int depth() {
        return t + 1;
    };

    /**
	 * Empties this stack.
	 */
    public void clear() {
        while (t >= 0) {
            pop();
        }
        t = 0;
    };

    /**
	 * Returns a new, empty stack with the same capacity as this stack.
	 * @return a new, empty stack with the same capacity as this stack
	 */
    public BDDStack<E> newInstance() {
        return new ArrayStack<E>(capacity());
    };
}
