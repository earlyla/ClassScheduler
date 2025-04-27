package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;
 
/**
 * Custom stack interface (as a LinkedList) which allows a user to push (add an element to top of stack), pop (remove an element
 * from the top of the stack), see if the stack isEmpty() (true if no size() is 0), check the size() (number
 * of elements added to stack), and set the capacity of the stack.
 * 
 * @param <E> generic type param
 * @author Luke Early 
 */
public class LinkedStack<E> implements Stack<E> {
	/** ArrayList for the stack */
	private LinkedAbstractList<E> list;
	/** capacity for stack */
	private int capacity;
	
	/**
	 * Constructor for LinkedStack, sets size to 0
	 * 
	 * @param capacity max number of elements to be added to stack
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		
		setCapacity(capacity);
	}
	
	/**
	 * Adds the specified element to the top of the stack.
	 * 
	 * @param element to be added to the top of the stack
	 * @throws IllegalArgumentException if the size() is equal to capacity of stack (stack is full)
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		
		list.add(0, element);
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if isEmpty() returns true
	 */
	@Override
	public E pop() {
		if (list.size() == 0) {
			throw new EmptyStackException();
		}
		
		E value = list.remove(0);
	
		return value;
	}

	/**
	 * Returns true if size() returns 0, false otherwise
	 * 
	 * @return true if stack is empty, false if stack is not
	 */
	@Override
	public boolean isEmpty() {
		int listSize = 0;
		
		try {
			listSize = list.size();
			return listSize == 0;
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}

	/**
	 * returns the number of elements currently in the stack.	
	 * 
	 * @return size of stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity (maximum number of elements in a stack).
	 * 
	 * @param capacity number of elements allowed in stack
	 * @throws IllegalArgumentException if capacity is negative
	 * @throws IllegalArgumentException if capacity is less than the number of elements
	 * 									in the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
		
	}
}
