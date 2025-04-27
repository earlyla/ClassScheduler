package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Customized ArrayList
 * 
 * @param <E> generic object type
 * @author Luke Early
 */
public class ArrayList<E> extends AbstractList<E> {
	/** size of the initial ArrayList */
	public static final int INIT_SIZE = 10;
	/** Generic type Array */
	private E[] list;
	/** number of elements in the Array */
	private int size;
	
	/**
	 * Constructor for ArrayList object.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Returns the number of elements in the ArrayList
	 * 
	 * @return size integer for the number of elementes in the ArrayList
	 */
	public int size() {
		return size;
	}

	/** 
	 * Returns the Object at index
	 * 
	 * @param index integer for the index of the target Object
	 * @return the Object at index
	 * @throws IndexOutOfBoundException if index less than 0, greater than or equal to size or list is null
	 */
	@Override
	public E get(int index) {
		E item = null;
		
		if (index < 0 || index >= size() || list == null) {
			throw new IndexOutOfBoundsException("Index not within array length");
		}
				
		for (int i = 0; i < list.length; i++) {
			if (i == index) {
				item = list[i];
			}
		}
		return item;
	}

	/**
	 * Adds an element at the specified index, adjusts the list based on where the object 
	 * was added.
	 * 
	 * @param index which is the index where the element will be inserted
	 * @throws NullPointerException if the element passed is null
	 * @throws IndexOutOfBoundsException if the index passed is less than 0 or greater than number of elements in the list
	 * @throws IllegalArgumentException if the element is already present in the list
	 * @param element the generic type object that will be inserted
	 */
	@Override
	public void add(int index, E element) {
		// Pre-conditions
		if (element == null) {
			throw new NullPointerException("Null element");
		}
		
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index not within array length");
		}
		
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException("Duplicate element");
			}
		}
		
		// resize check
		if (size == list.length) {
			growArray();
		}
		
		for (int i = size(); i > index; i--) {
			list[i] = list[i - 1];
		}
		
		list[index] = element;
		size++;
	}
	
	/**
	 * Removes the element at the specified index, adjusts the list based on where the
	 * element was removed.
	 * 
	 * @param index which is the index where the element will be removed
	 * @throws IndexOutOfBoundsException if the index passed is less than 0 or greater than number of elements in the list
	 * @return item the element that was removed
	 */
	@Override
	public E remove(int index) {
		// pre-conditions
		if (index < 0 || index >= size() || list == null) {
			throw new IndexOutOfBoundsException("Index not within array length");
		}
		
		E item = list[index];
		
		for (int i = index; i < size(); i++) {
			list[i] = list[i + 1];
		}
		
		size--;
		return item;
	}
	
	/**
	 * replaces the element at the specified list index with the parameter passed.
	 * 
	 * @param index the location in the list of the element to replace
	 * @param element the element that will replace the current element
	 * @throws NullPointerException if the element passed is null
	 * @throws IndexOutOfBoundsException if the index passed is less than 0 or greater than number of elements in the list
	 * @throws IllegalArgumentException if the element is already present in the list
	 * @return originalElement the element that was replaced on the list
	 */
	@Override
	public E set(int index, E element) {
		// Pre-conditions
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index >= size() || list == null) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException("Duplicate element");
			}
		}
		
		E originalElement = list[index]; // element to be replaced and returned
		
		list[index] = element;
		
		return originalElement;
	}
	
	/**
	 * Resizes array if the current size upon call to add() is the same as the list capacity.
	 * 
	 * Resize capacity will be 2x the capacity of the current array capacity
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int newCapacity = list.length * 2;
		
		E[] tempList = (E[]) new Object[newCapacity];
		
		for (int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		
		list = tempList;
		
	}
	
	/**
	 * Method returns the length of the list, for testing.
	 * 
	 * @return length of list as an integer
	 */
	public int getListLength() {
		return list.length;
	}	
	
}
