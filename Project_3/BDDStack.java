package Project_3;
/**
 * A bounded stack is a bounded, last-in-first-out (LIFO) collection of non-null elements.
 * <p>
 * Any concrete class that implements this interface should have the following
 * one argument constructor:
 * <pre>{@code
 *     public MyStack(int max) throw IllegalArgumentException;
 * }</pre>
 * The argument {@code max} will be the capacity of the stack, and the constructor will
 * throw an {@code IllegalArgumentException} if {@code max <= 0}.
 * <p>
 * A typical bounded stack is {@code [e1, e2, ..., ek]:N} where {@code e1..ek} are
 * the elements of the stack from bottom to top, and {@code N} is the capacity of the stack.
 * An iterater should traverse from {@code e1} to {@code ek}.
 * 
 * @author Bo Mei
 * @param <E> the type of elements in this stack
 */
public interface BDDStack<E> extends Iterable<E> {
	
	// ========================================
	// Primary Methods
	// ========================================
	
	/**
	 * Pushes the specified element onto this stack.
	 * @param element the element to be pushed
	 * @throws NullPointerException if {@code element == null}
	 * @throws IllegalStateException if this stack is full
	 */
	void push(E element) throws NullPointerException, IllegalStateException;
	
	/**
	 * Removes and returns the element at the top of this stack.
	 * @return the element at the top of this stack
	 * @throws IllegalStateException if this stack is empty
	 */
	E pop() throws IllegalStateException;
	
	/**
	 * Returns the depth of this stack.
	 * Depth is the number of actual elements stored in the stack.
	 * @return the depth of this stack
	 */
	int depth();
	
	/**
	 * Empties this stack.
	 */
	void clear();
	
	/**
	 * Returns a new, empty stack with the same capacity as this stack.
	 * @return a new, empty stack with the same capacity as this stack
	 */
	BDDStack<E> newInstance();
	
	// ========================================
	// Secondary Methods
	// ========================================
	
	/**
	 * Returns the capacity of this stack.
	 * @return the capacity of this stack
	 */
	int capacity();
	
	/**
	 * Returns true if this stack is empty.
	 * @return true if this stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns true if this stack is full.
	 * @return true if this stack is full
	 */
	boolean isFull();
	
	/**
	 * Reverses the elements in this stack.
	 */
	void flip();
	
	/**
	 * Returns a new stack that is a shallow copy of this stack. The new stack
	 * has the same capacity as this stack.
	 * @return a new stack that is a shallow copy of this stack
	 */
	BDDStack<E> copy();
}
