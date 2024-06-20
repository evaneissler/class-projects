package Project_3;

public abstract class AbstractStack<E> implements BDDStack<E> {

    protected int capacity;

    /**
     * Constructor to store capacity
     * @param capacity the size of the stack created
     */
    public AbstractStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    /**
	 * Returns the capacity of this stack.
	 * @return the capacity of this stack
	 */
    public int capacity() {
        return capacity;
    };

    /**
	 * Returns true if this stack is empty.
	 * @return true if this stack is empty
	 */
    public boolean isEmpty() {
        return depth() == 0;
    };

    /**
	 * Returns true if this stack is full.
	 * @return true if this stack is full
	 */
    public boolean isFull() {
        return depth() == capacity();
    };

    /**
	 * Reverses the elements in this stack.
	 */
    public void flip() {
        BDDStack<E> temporaryStack = newInstance();
        BDDStack<E> temporaryStack2 = newInstance();
        while (!isEmpty()) {
            temporaryStack.push(pop());
        }
        while (!temporaryStack.isEmpty()) {
            temporaryStack2.push(temporaryStack.pop());
        }
        while (!temporaryStack2.isEmpty()) {
            push(temporaryStack2.pop());
        }
    };

    /**
	 * Returns a new stack that is a shallow copy of this stack. The new stack
	 * has the same capacity as this stack.
	 * @return a new stack that is a shallow copy of this stack
	 */
    public BDDStack<E> copy() {
        BDDStack<E> newStack = newInstance();
        BDDStack<E> temporaryStack = newInstance();
        while (!isEmpty()) {
            temporaryStack.push(pop());
        }
        while (!temporaryStack.isEmpty()) {
            E element = temporaryStack.pop();
            push(element);
            newStack.push(element);
        }
        return newStack;
    };
}
