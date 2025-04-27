package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom stack interface which allows a user to push (add an element to top of stack), pop (remove an element
 * from the top of the stack), see if the stack isEmpty() (true if no size() is 0), check the size() (number
 * of elements added to stack), and set the capacity of the stack.
 * 
 * @param <E> generic type declaration
 * 
 * @author Luke Early
 */
public interface Stack<E> {
	
	/**
	 * Adds the specified element to the top of the stack.
	 * 
	 * @param element to be added to the top of the stack
	 * @throws IllegalArgumentException if the size() is equal to capacity of stack (stack is full)
	 */
	void push(E element);
	
	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if isEmpty() returns true
	 */
	E pop();
	
	/**
	 * Returns true if size() returns 0, false otherwise
	 * 
	 * @return true if stack is empty, false if stack is not
	 */
	boolean isEmpty();
	
	/**
	 * returns the number of elements currently in the stack.	
	 * 
	 * @return size of stack
	 */
	int size();
	
	/**
	 * Sets the capacity (maximum number of elements in a stack).
	 * 
	 * @param capacity number of elements allowed in stack
	 * @throws IllegalArgumentException if capacity is negative
	 * @throws IllegalArgumentException if capacity is less than the number of elements
	 * 									in the stack
	 */
	void setCapacity(int capacity);

}
