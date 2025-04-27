package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * LinkedAbstractList extends AbstractList and implements a LinkedList 
 * made up of ListNodes.
 * 
 * @param <E> generic type parameter for LinkedAbstractList
 * @author Luke Early
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** number of elements in the LinkedList */
	private int size;
	/** number of elements the LinkedList is able to hold */
	private int capacity;
	/** The first node in the LinkedList */
	private ListNode front;
	/** The final node in the LinkedList */
	private ListNode back;
	
	/**
	 * Constructs a LinkedList where front is set to null, 
	 * size is set to 0, and capacity is set to whatever is
	 * passed as the parameter.
	 * 
	 * @param capacity max number of elements allowed in the list.
	 * @throws IllegalArgumentException 1. if the capacity passed is less
	 * 		   						    than 0.
	 * 									2. if the capacity is less than the
	 * 									current list's size.
	 * 
	 */
	public LinkedAbstractList(int capacity) {
		size = 0;
		
		front = null;
				
		try {
			this.setCapacity(capacity);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
				
	}
	
	/**
	 * Sets the capacity of this list.
	 * 
	 * Ensures capacity is greater than/equal to 0 and greater than size.
	 * 
	 * @param capacity capacity of this list.
	 * @throws IllegalArgumentException 1. if the capacity passed is less
	 * 		   						    than 0.
	 * 									2. if the capacity is less than the
	 * 									current list's size.
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}

	/**
	 * This method locates and returns the element at the specified index.
	 * 
	 * @param index position in the list of target element
	 * @return element at specified index
	 * @throws IndexOutOfBoundsException if index passed is less than 0
	 * 									 or greater than size.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode curr = front;
		
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		
		return curr.data;
	}
	
	/**
	 * Replaces the element at the specified index with a new element that has 
	 * the passed data. Returns the replaced element.
	 * 
	 * @param index of the element to be replaced
	 * @param data of the new element
	 * @return element that was removed
	 * @throws NullPointerException if element is set to null
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 * @throws IllegalArgumentException if element is a duplicate (determine through equals())
	 * 
	 */
	@Override
	public E set(int index, E data) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (data == null) {
			throw new NullPointerException();
		}
		
		ListNode test = front;
		
		for (int i = 0; i < size(); i++) {
			if (test.data.equals(data)) {
				throw new IllegalArgumentException();
			}
			
			test = test.next;
		}
		
		E value = null;
		
		if (index == 0) {
			ListNode temp = front;
			value = front.data;
			front = new ListNode(data, front);
			front.next = temp.next;
			
		} else if (index == size() - 1) {
			ListNode curr = front;
			
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;			
			}
			
			value = curr.next.data;
			
			curr.next = new ListNode(data);
			
		} else {			
			ListNode curr = front;
			
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;			
			}
			
			ListNode temp = curr.next.next;
			value = curr.next.data;
			curr.next = new ListNode(data, temp);
		}
		
		return value;
	}
	
	/**
	 * Adds an element with specified data to list at specified index.
	 * 
	 * @param index location in list to add element
	 * @param data data that the node will have
	 * @throws IllegalArgumentException if size is the same as capacity (list is full)
	 * @throws NullPointerException if element is null
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 * @throws IllegalArgumentException if element is a duplicate (determine through equals())
	 * 
	 */
	public void add(int index, E data) {
		if (size() == capacity) {
			throw new IllegalArgumentException("The list is full");
		}
		
		if (data == null) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// check if element already in list
		ListNode test = front;
		
		for (int i = 0; i < size(); i++) {
			if (test.data.equals(data)) {
				throw new IllegalArgumentException();
			}
			
			test = test.next;
		}
		
		if (size() == 0) {
			front = new ListNode(data, front);
			back = front;
		} else if (index == size()) { 
			ListNode elementToAdd = new ListNode(data);
			back.next = elementToAdd;
			back = elementToAdd;
		} else if (index == 0) {
			front = new ListNode(data, front);
		} else {
			ListNode curr = front;
			
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			curr.next = new ListNode(data, curr.next);
			
		}
		
		size++;
	}
	
	/**
	 * Removes item at index and returns item.
	 * 
	 * @param index index of item to be removed
	 * @return item that was removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than/equal to size
	 */
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E value = null;
		
		if (index == 0) {
			value = front.data;
			front = front.next;
		} else if (index == size() - 1) {
			ListNode curr = front;
			
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			value = curr.next.data;
			curr.next = curr.next.next;
			back = curr;
			
		} else { 
			ListNode curr = front;
				
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}
			value = curr.next.data;
			curr.next = curr.next.next;
		}
		size--;
		return value;
	}
	
	/**
	 * Returns this list's current size parameter.
	 * 
	 * @return size the number of elements currently in the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * ListNode inner class that constructs ListNode objects that make up 
	 * LinkedLists. ListNodes know a piece of data and a reference to the 
	 * next ListNode or null reference if there is no next ListNode.
	 * 
	 * @author Luke Early
	 */
	public class ListNode {
		/** generic data for the ListNode */
		public E data;
		/** reference to the next ListNode */
		public ListNode next;
		
		/**
		 * Constructor for a ListNode added to the end of a LinkedList.
		 * 
		 * @param data to be added to the ListNode
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructor for a ListNode to be added anywhere in the LinkedList
		 * with a client specified reference.
		 * 
		 * @param data to be added to the ListNode
		 * @param next reference to the next ListNode in the LinkedList
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
