package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {
	
	/**
	 * Tests the default ArrayList constructor
	 */
	@Test
	void defaultConstructorTest() {
		ArrayList<String> list = new ArrayList<String>();
		
		assertEquals(list.size(), 0);
	}
	
	/**
	 * Tests the add(index, element) and get(index) methods
	 */
	@Test
	void testAddAndGet() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		// add to end of list
		list.add(0, "The");
		list.add(1, "Array");
		list.add(2, "List");
		
		assertEquals(list.get(0), "The");
		assertEquals(list.get(1), "Array");
		assertEquals(list.get(2), "List");
		
		// add to middle of list
		list.add(1, "New");
		
		assertEquals(list.get(0), "The");
		assertEquals(list.get(1), "New");
		assertEquals(list.get(2), "Array");
		assertEquals(list.get(3), "List");
		
		// add to beginning of list
		list.add(0, "It's");
		
		assertEquals(list.get(0), "It's");
		assertEquals(list.get(1), "The");
		assertEquals(list.get(2), "New");
		assertEquals(list.get(3), "Array");
		assertEquals(list.get(4), "List");
		
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	void testRemove() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		list.add(0, "It's");
		list.add(1, "The");
		list.add(2, "New");
		list.add(3, "Array");
		list.add(4, "List");
		
		assertEquals(list.get(0), "It's");
		assertEquals(list.get(1), "The");
		assertEquals(list.get(2), "New");
		assertEquals(list.get(3), "Array");
		assertEquals(list.get(4), "List");
		assertEquals(list.size(), 5);
		
		// remove from end
		assertEquals(list.remove(4), "List");
		assertEquals(list.size(), 4);
		
		
		// remove from beginning
		assertEquals(list.remove(0), "It's");
		assertEquals(list.size(), 3);
		
		// remove from middle
		assertEquals(list.remove(1), "New");
		assertEquals(list.size(), 2);
	}

	/**
	 * Tests the set(index, element) method
	 */
	@Test
	void testSet() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		list.add(0, "The");
		list.add(1, "Array");
		list.add(2, "List");
		
		assertEquals(list.get(0), "The");
		assertEquals(list.get(1), "Array");
		assertEquals(list.get(2), "List");
		assertEquals(list.size(), 3);
		
		assertEquals(list.set(0, "My"), "The");
		assertEquals(list.size(), 3);
	}
	
	/**
	 * Tests the private growArray() method and the getListLength() method
	 */
	@Test
	void testResize() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		list.add(0, "It's");
		list.add(1, "The");
		list.add(2, "New");
		list.add(3, "Array");
		list.add(4, "List");
		list.add(5, "That");
		list.add(6, "I");
		list.add(7, "Designed");
		list.add(8, "Yay!");
		list.add(9, "Wow!");
		
		assertEquals(list.get(0), "It's");
		assertEquals(list.get(1), "The");
		assertEquals(list.get(2), "New");
		assertEquals(list.get(3), "Array");
		assertEquals(list.get(4), "List");
		assertEquals(list.get(5), "That");
		assertEquals(list.get(6), "I");
		assertEquals(list.get(7), "Designed");
		assertEquals(list.get(8), "Yay!");
		assertEquals(list.get(9), "Wow!");
		
		assertEquals(list.size(), 10);
		assertEquals(list.getListLength(), 10);
		
		list.add(10, "Resize Time!");
		assertEquals(list.get(0), "It's");
		assertEquals(list.get(1), "The");
		assertEquals(list.get(2), "New");
		assertEquals(list.get(3), "Array");
		assertEquals(list.get(4), "List");
		assertEquals(list.get(5), "That");
		assertEquals(list.get(6), "I");
		assertEquals(list.get(7), "Designed");
		assertEquals(list.get(8), "Yay!");
		assertEquals(list.get(9), "Wow!");
		assertEquals(list.get(10), "Resize Time!");
		
		assertEquals(list.size(), 11);
		assertEquals(list.getListLength(), 20);
	}
	
	@Test
	void testSetInEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		assertThrows(IndexOutOfBoundsException.class, 
						() -> list.set(0, "the"));
	}
	
	@Test
	void testRemoveFromEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(0));
		
		assertThrows(IndexOutOfBoundsException.class, 
						() -> list.remove(1));
	}
	
	@Test
	void testGetOutsideListSize() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(list.size(), 0);
		
		list.add(0, "It's");
		list.add(1, "The");
		list.add(2, "New");
		list.add(3, "Array");
		assertEquals(list.size(), 4);
		
		assertThrows(IndexOutOfBoundsException.class, 
						() -> list.get(4));
	}
	
}
