package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue interface that allows a client to add an element (enqueue()) to the back of
 * the list, remove and return an element (dequeue()) from the front of the list, check
 * if a Queue isEmpty(), return a Queue's size(), and setCapacity() of the Queue.
 * 
 * @param <E> generic type declaration
 * @author Luke Early
 */
public interface Queue<E> {

	/**
	 * Adds an element to the back of the Queue.
	 * 
	 * @param element to add to the back of the Queue.
	 * @throws IllegalArgumentException if the size() of the Queue is 
	 * 								    the same as the Queues capacity
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the first element in the Queue.
	 * 
	 * @return first element in the Queue
	 * @throws NoSuchElementException if the Queue.size() is 0
	 */
	E dequeue();
	
	/**
	 * A boolean that returns true if the Queue is empty, and false if 
	 * otherwise.
	 * 
	 * @return true if Queue.size() is 0, false if otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Returns the Queues size, or the number of elements in the Queue
	 * 
	 * @return size field of the Queue
	 */
	int size();
	
	/**
	 * Sets the Queue's capacity, or max number of elements a client can store in a Queue.
	 * 
	 * @param capacity of the queue, max number of elements a client can store in a Queue
	 * @throws IllegalArgumentException if parameter is less than 0 or less than size()
	 */
	void setCapacity(int capacity);
}
