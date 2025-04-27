/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * LinkedList mad up of ListNodes which uses an iterator to traverse the list.
 * 
 * Allows a client to add and set elements.
 * 
 * @param <E> generic type parameter
 * @author Luke Early
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** size of the list */
	private int size;
	/** first node in the list */
	private ListNode front;
	/** last node in the list*/
	private ListNode back;
	
	/** 
	 * Constructor for linked list
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		
		front.next = back;
		back.prev = front;

		size = 0;
	}
	
	/**
	 * Allows client to set an element to the specified element at the given index
	 * 
	 * @param index location of insertion
	 * @param element element to insert at index
	 * @return E
	 */
	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		ListIterator<E> myItr = listIterator(index);
		
		myItr.next();
		myItr.set(element);
		
		return element;
	}
	
	/**
	 * returns number of elements on the list
	 * 
	 * @return number of elements on this LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds an element at the given index.
	 * 
	 * @param index location to add element
	 * @param element element to add at location
	 * @throws IllegalArgumentException if element is already in the list
	 */
	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		ListIterator<E> myItr = listIterator(index);
		myItr.add(element);
	}
	
	/**
	 * Returns an iterator at the location specified by the index.
	 * 
	 * return listIterator at the index specified
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> listIterator = new LinkedListIterator(index);
		
		return listIterator;
	}
	
	/**
	 * ListNodes make up LinkedList objects. This implementation has a front reference
	 * pointing to the first element, an a back reference pointing to the last element.
	 * 
	 * @author Luke Early
	 */
	private class ListNode {
		/** data of the elements passed*/
		public E data;
		/** points to the element that immediately follows this element */
		public ListNode next;
		/** points to the element that is right before this element */
		public ListNode prev;
		
		/**
		 * Constructs a ListNode with data and null next and prev references
		 * 
		 * @param data the data that the ListNode will have
		 */
		public ListNode(E data) {
			this(data, null, null);
		}
		
		/**
		 * Constructs a ListNode object
		 * 
		 * @param data in the ListNode
		 * @param prev ListNode before this ListNode
		 * @param next ListNode after this ListNode
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	/**
	 * Represents the current location in the LinkedList. Allows a client to traverse a LinkedList
	 * in both directions.
	 * 
	 * @author Luke Early
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** reference to the previous ListNode */
		public ListNode previous;
		/** reference to the next ListNode */
		public ListNode next;
		/** index of the previous element */
		public int previousIndex;
		/** index of the next element*/
		public int nextIndex;
		/** ListNode returned after call to previous()/next(), null if neither were called */
		private ListNode lastRetrieved;
		
		/**
		 * Iterator for the LinkedList. This allows a client to traverse a LinkedList
		 * linear data structure.
		 * 
		 * @param index index
		 * @throws IndexOutOfBoundsException if index is less than zero or greater than size
		 */
		public LinkedListIterator(int index) { 
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			
			if (size() == 0) {
				previous = front;
				this.next = back;
			} else {
				previous = front;
				this.next = front.next;
			}
			
			for (int i = 0; i < index; i++) {
				previous = previous.next;
				this.next = this.next.next;
			}
			
			previousIndex = index - 1;
			nextIndex = index;
			
			lastRetrieved = null;
		}
		
		/**
		 * Returns true if next() returns an element
		 * 
		 * @return true if next() returns an element, false if otherwise 
		 */
		@Override
		public boolean hasNext() {
			try {
				return next.data != null;
			} catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * Returns the element in the next ListNode
		 * 
		 * @return element the next element in the list
		 */
		@Override
		public E next() {
			E element;
			
			if (next.data == null) {
				throw new NoSuchElementException();				
			} else {
				element = next.data;
				lastRetrieved = next;
			
				this.next = this.next.next;
				previous = previous.next;
			}
			
			return element;
		}
		
		/**
		 * Returns index of the next element
		 * 
		 * @return int index of next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * Returns true if previous() has an element
		 * 
		 * @return true if previous() has an element, false otherwise
		 */
		@Override
		public boolean hasPrevious() {
			try {
				return previous() != null;
			} catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * Returns the element that the previous reference points to.
		 * 
		 * @return element at the previous location
		 * @throws NoSuchElementException if the element is null
		 */
		@Override
		public E previous() {
			E element;
			
			if (previous.data == null) {
				throw new NoSuchElementException();
			} else {
				element = previous.data;
				lastRetrieved = previous;
				
				previous = previous.prev;
				next = next.prev;
			}
				
			return element;
		}
		
		/**
		 * Returns the index of the previous element
		 * 
		 * @return index of previous element
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}
		
		/**
		 * Removes the lastRetrieved element
		 * 
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			ListNode newPrev = lastRetrieved.prev;
			ListNode newNext = lastRetrieved.next;
			
			// set old previous next to newNext
			// set old previous prev t
			newPrev.next = newNext;
			newNext.prev = newPrev;
			lastRetrieved = null;
			size--;
			
		}
		
		/**
		 * Replaces the element lastRetrieved by next() or previous().
		 * 
		 * @throws IllegalStateException if lastRetrieved is null
		 * @throws NullPointerException if element is null
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (element == null) {
				throw new NullPointerException();
			}
			
			ListNode holdPrev = lastRetrieved.prev;
			ListNode holdNext = lastRetrieved.next;
			
			ListNode elementToSet = new ListNode(element, lastRetrieved.prev, lastRetrieved.next);
			holdPrev.next = elementToSet;
			holdNext.prev = elementToSet;
		}
		
		/**
		 * adds element e at the next() location the iterator is at.
		 * 
		 * @throws NullPointerException if element is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			
			ListNode elementToAdd;
			
			if (size() == 0) {
				// make new element, set fields to LinkedList front and back
				elementToAdd = new ListNode(e, front, back);
				
				// ensure the linked list sees the object as well
				front.next = elementToAdd;
				back.prev = elementToAdd;
			} else if (nextIndex() == size()) {
				// create the element, point previous to iterator previous and iterator next
				elementToAdd = new ListNode(e, previous, next);
				
				next = elementToAdd;
				previous.next = elementToAdd;
				back.prev = elementToAdd;
			} else if (nextIndex == 0) {
				ListNode oldPrev = previous;
				ListNode oldNext = next;
				elementToAdd = new ListNode(e, previous, next);
				oldPrev.next = elementToAdd;
				oldNext.prev = elementToAdd;
			} else {
				elementToAdd = new ListNode(e, previous, next);
				next.prev = elementToAdd;
				previous.next = elementToAdd;
			}
			
			lastRetrieved = null;
			size++;
		}
	}
}
