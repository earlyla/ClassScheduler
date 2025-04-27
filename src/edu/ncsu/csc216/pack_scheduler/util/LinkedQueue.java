package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queue interface, with a LinkedList, that allows a client to add an element (enqueue()) to the back of
 * the list, remove and return an element (dequeue()) from the front of the list, check
 * if a Queue isEmpty(), return a Queue's size(), and setCapacity() of the Queue.
 *
 * 
 * @param <E> generic type declaration
 * @author Luke Early
 */
public class LinkedQueue<E> implements Queue<E> {
	/** LinkedList underlying the Queue */
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructor for LinkedQueue with a parameter to set capacity
	 * 
	 * @param capacity max number of elements in the Queue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds an element to the back of the Queue.
	 * 
	 * @param element to add to the back of the Queue.
	 * @throws IllegalArgumentException if the size() of the Queue is 
	 * 								    the same as the Queues capacity
	 */
	@Override
	public void enqueue(E element) {
		list.add(list.size(), element);
	}
	
	/**
	 * Removes and returns the first element in the Queue.
	 * 
	 * @return first element in the Queue
	 * @throws NoSuchElementException if the Queue.size() is 0
	 */
	@Override
	public E dequeue() {
		if (list.size() == 0) {
			throw new NoSuchElementException();
		}
		
		return list.remove(0);
	}
	
	/**
	 * A boolean that returns true if the Queue is empty, and false if 
	 * otherwise.
	 * 
	 * @return true if Queue.size() is 0, false if otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * Returns the Queues size, or the number of elements in the Queue
	 * 
	 * @return size field of the Queue
	 */
	@Override
	public int size() {
		try {
			return list.size();
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Sets the Queue's capacity, or max number of elements a client can store in a Queue.
	 * 
	 * @param capacity of the queue, max number of elements a client can store in a Queue
	 * @throws IllegalArgumentException if parameter is less than 0 or less than size()
	 */
	@Override
	public void setCapacity(int capacity) {
		try {
			list.setCapacity(capacity);
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * returns true if the element specified is already in the 
	 * list.
	 * 
	 * @param element to check for
	 * @return true if element is on list, false if not
	 */
	public boolean contains(E element) {
		for (int i = 0; i < list.size(); i++) {
			if (element.equals(list.get(i))) {
				return true;
			}
		}
		
		return false;
	}
}
