package Project_3;

import java.util.Iterator;
import java.util.ArrayList;

public class ListStack<E> extends AbstractStack<E> implements Iterable<E> {

    private ArrayList<E> myStack;
    private int t = -1;

    /**
     * Creates new ArrayList instance with the given capacity 
     * @param max The capacity for the new ArrayList being created
     * @throws IllegalArgumentException
     */
    public ListStack(int max) throws IllegalArgumentException {
        super(max);
        myStack = new ArrayList<>();
    }

    /**
     * Implements the Iterator object to iterate through each element in the stack using interface
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;

            public boolean hasNext() {
                return current <= t;
            }

            public E next() {
                return myStack.get(current++);
            }
        };
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
            myStack.add(++t, element);
        }
    }

    /**
	 * Removes and returns the element at the top of this stack.
	 * @return the element at the top of this stack
	 * @throws IllegalStateException if this stack is empty
	 */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        } else {
            E temp = myStack.remove(t--);
            return temp;
        }
    }

    /**
	 * Returns the depth of this stack.
	 * Depth is the number of actual elements stored in the stack.
	 * @return the depth of this stack
	 */
    public int depth() {
        return t + 1;
    }

    /**
	 * Empties this stack.
	 */
    public void clear() {
        myStack.clear();
        t = -1;
    }

    /**
	 * Returns a new, empty stack with the same capacity as this stack.
	 * @return a new, empty stack with the same capacity as this stack
	 */
    public ListStack<E> newInstance() {
        return new ListStack<>(capacity());
    }
}
