package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive Linked List
 * 
 * @param <E> generic type parameter
 * @author Luke Early
 */
public class LinkedListRecursive<E> {
	/** size */
	private int size;
	/** front node */
	private ListNode front;

	/**
	 * Constructor for LinkedListRecursive. Initializes a null front node and size of 0
	 */
	public LinkedListRecursive() {
		front = null;
		this.size = 0;
	}
	
	/**
	 * Returns boolean result of size == 0.
	 * 
	 * @return true if size is 0, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns number of elements currently in the list
	 * 
	 * @return size and int representing the number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds element to the end of the list
	 *  
	 * @param element to be added
	 * @return true if it was added, false if it was not
 	 * @throws IllegalArgumentException if element is already in list
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException("already in list bruh");
		} else if (size() == 0) {
			front = new ListNode(element, null);
			size++;
			return true;
		} else {
			return front.add(element);
		}
	}
	
	/**
	 * Adds the passed element at the specified index
	 * 
	 * @param index location to add ListNode
	 * @param element element to store in ListNode
	 * @throws IllegalArgumentException if element is already in list
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
	 * @throws NullPointerException if element passed is null
	 */
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException();			
		} else if (index == 0) {
			front = new ListNode(element, front);
			size++;
		} else if (front == null) {
			throw new IndexOutOfBoundsException();
		} else {
			front.add(index - 1, element);
		}
		
	}
	
	/**
	 * Returns the element at the given index.
	 * 
	 * @param index of element to return in list
	 * @return element at specified index
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
	 */
	public E get(int index) {
		
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return front.get(index);
		}
	}
	
	/**
	 * Removes specified element and returns true. If element is not present, returns false.
	 * 
	 * @param element to remove
	 * @return true if element was removed, false if it wasn't
	 * @throws NullPointerException if element is null
	 */
	public boolean remove(E element) {
		if (element == null) {
			return false;
		} 
		
		if (isEmpty()) {
			return false;
		}
		
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(element);
		}
	}
	
	/**
	 * Removes element at specified index
	 * 
	 * @param index location of element to remove
	 * @return element removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
	 */
	public E remove(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (front == null) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			E removed = front.data;
			front = front.next;
			size--;
			return removed;
		} else {
			return front.remove(index - 1);
		}
	}
	
	/**
	 * Replaces the element at index with the specified element.
	 * 
	 * @param index location of element to be replaced
	 * @param element element to replace current element
	 * @return replaced element
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (front == null) {
			throw new IndexOutOfBoundsException();
		} else {
			return front.set(index - 1, element);
		}
	}
	
	/**
	 * Returns true if the passed element is in the LinkedList and false if the element is not present.
	 * 
	 * @param element to search for in the LinkedList
	 * @return true if list contains element, false if it doesn't
	 */
	public boolean contains(E element) {
		if (size() == 0) {
			return false;
		} else {
			return front.contains(element);
		}
	}
	
	/**
	 * ListNode inner class, the node contains data and a reference to the next node in the linked list
	 * 
	 * @author Luke Early
	 */
	private class ListNode {
		/** data to be stored in the node */
		public E data;
		/** reference to the next node in the list */
		public ListNode next;
		
		/**
		 * ListNode constructor which sets its data and next reference
		 * to the passed parameters.
		 * 
		 * @param data to set for the ListNode
		 * @param next next reference for the node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the element to the specified index
		 * 
		 * @param index location in list to add element
		 * @param element element to add to list
		 * @throws IndexOutOfBoundsException if next reference is null
		 */
		public void add(int index, E element) {
			if (index == 0) {
				next = new ListNode(element, next);
				size++;
			} else if (next == null) {
				throw new IndexOutOfBoundsException();
			} else {
				next.add(index - 1, element);
			}
		}
		
		/**
		 * Adds element to the end of the list.
		 * 
		 * @param element element to add to the end of the list
		 * @return true if element was added, false if it was not
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			} else {
				return next.add(element);
			}
		}
		
		/**
		 * Returns element at specified index.
		 * 
		 * @param index location in list of element
		 * @return element at specified index
		 */
		public E get(int index) {
			if (index == 0) {
				return this.data;
			} else if (next == null) {
				throw new IndexOutOfBoundsException();
			} else {
				return next.get(index - 1);
			}
		}
		
		/**
		 * Removes element at specified index
		 * 
		 * @param index location of element to remove
		 * @return element removed
		 */
		public E remove(int index) {
			if (next == null) {
				throw new IndexOutOfBoundsException();
			} else if (index == 0) {
				E removed = next.data;				
				this.next = next.next;
				size--;
				return removed;
			} else {
				return next.remove(index - 1);
			}
		}
		
		/**
		 * Removes specified element and returns true. If element is not present, returns false.
		 * 
		 * @param element to remove
		 * @return true if element was removed, false if it wasn't
		 */
		public boolean remove(E element) {
			if (next == null) {
				// IOOBE?
				throw new IndexOutOfBoundsException();
			} else if (next.data.equals(element)) {
				this.next = next.next;
				size--;
				return true;
			} else if (!this.data.equals(element)) {
				return next.remove(element);
			} else {
				return false;
			}
		}
		
		/**
		 * Replaces the element at index with the specified element.
		 * 
		 * @param index location of element to be replaced
		 * @param element element to replace current element
		 * @return replaced element
		 * @throws IndexOutOfBoundsException if next element is null
		 */
		public E set(int index, E element) {
			if (index == -1) {
				E replaced = front.data;
				ListNode elementToInsert = new ListNode(element, next);
				front = elementToInsert;
				return replaced;
			} else if (index == 0) {
				E replaced = next.data;
				ListNode elementToInsert = new ListNode(element, next.next);
				this.next = elementToInsert;
				return replaced;
			} else if (next == null) {
				throw new IndexOutOfBoundsException();
			} else {
				return next.set(index - 1, element);
			}
		}
		
		/**
		 * Searches for the element in the list
		 * 
		 * @param element to search for
		 * @return true if the element is in the list, false if it is not
		 */
		public boolean contains(E element) {
			if (this.data == null) {
				return false;
			} else if (this.data.equals(element)) { // base case found
				return true;
			} else if (this.next != null) {
				return this.next.contains(element);
			}
			return false;
		}
	}
}

