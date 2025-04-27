/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedAbstractList class
 * 
 * @author Luke Early
 */
class LinkedAbstractListTest {

	/**
	 * Tests the size() method
	 */
	@Test
	void testSize() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
	}

	/**
	 * Tests the LinkedAbstractList constructor.
	 */
	@Test
	void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		assertThrows(IllegalArgumentException.class,
				() -> new LinkedAbstractList<String>(-1));
	}

	/**
	 * Tests the setCapacity() method.
	 */
	@Test
	void testSetCapacity() {
		// New empty list
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		list.add(0, "Apple");
		list.add(1, "Banana");
		list.add(2, "Orange");
		list.add(3, "Peach");
		
		assertThrows(IllegalArgumentException.class, 
						() -> list.setCapacity(2));
		
	}

	/**
	 * Tests the get() method
	 */
	@Test
	void testGet() {
		// New empty list
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		// test high index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.get(1));
		
		// test -1 index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.get(-1));
	}

	/**
	 * Tests the set() method
	 */
	@Test
	void testSet() {
		// New empty list
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		// Add to beginning
		list.add(0, "Apple");
		assertEquals("Apple", list.get(0));
		assertEquals(1, list.size());
		list.add(1, "Banana");
		assertEquals("Banana", list.get(1));
		assertEquals(2, list.size());
		list.add(2, "Orange");
		assertEquals("Orange", list.get(2));
		assertEquals(3, list.size());
		list.add(3, "Peach");
		assertEquals("Peach", list.get(3));
		assertEquals(4, list.size());

		assertEquals("Apple", list.get(0));
		assertEquals("Banana", list.get(1));
		assertEquals("Orange", list.get(2));
		assertEquals("Peach", list.get(3));
		
		// set in the middle
		assertEquals("Banana", list.set(1, "Grape"));
		assertEquals("Grape", list.get(1));
		
		// set in the front
		assertEquals("Apple", list.set(0, "Melon"));
		assertEquals("Melon", list.get(0));
		
		// set in the back
		assertEquals("Peach", list.set(3, "Berry"));
		assertEquals("Berry", list.get(3));
		
		// test null element
		assertThrows(NullPointerException.class, 
						() -> list.set(1, null));
		
		// test high index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.set(8, "Strawberry"));
		
		// test -1 index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.set(-1, "Strawberry"));
		
		// test already present
		assertThrows(IllegalArgumentException.class, 
				() -> list.set(1, "Grape"));		
	}

	/**
	 * Tests the add() method.
	 */
	@Test
	void testAdd() {
		// New empty list
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		// Add to beginning
		list.add(0, "Apple");
		assertEquals("Apple", list.get(0));
		assertEquals(1, list.size());
		
		// Add to end
		list.add(1, "Banana");
		assertEquals("Banana", list.get(1));
		assertEquals(2, list.size());
		
		// Add more to end
		list.add(2, "Orange");
		assertEquals("Orange", list.get(2));
		assertEquals(3, list.size());
		
		list.add(3, "Peach");
		assertEquals("Peach", list.get(3));
		assertEquals(4, list.size());
		
		// Add to middle
		list.add(1, "Pear");
		assertEquals("Pear", list.get(1));
		assertEquals(5, list.size());
		
		assertEquals("Banana", list.get(2));
		assertEquals("Orange", list.get(3));
		assertEquals("Peach", list.get(4));		
		
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	void testRemove() {
		// New empty list
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		
		// Add to beginning
		list.add(0, "Apple");
		assertEquals("Apple", list.get(0));
		assertEquals(1, list.size());
		
		// Add to end
		list.add(1, "Banana");
		assertEquals("Banana", list.get(1));
		assertEquals(2, list.size());
		
		// Add more to end
		list.add(2, "Orange");
		assertEquals("Orange", list.get(2));
		assertEquals(3, list.size());
		
		list.add(3, "Peach");
		assertEquals("Peach", list.get(3));
		assertEquals(4, list.size());
		
		// Add to middle
		list.add(1, "Pear");
		assertEquals("Pear", list.get(1));
		assertEquals(5, list.size());
		
		// Remove
		// Remove from end
		assertEquals("Peach", list.remove(4));
		assertEquals("Apple", list.get(0));
		assertEquals("Pear", list.get(1));
		assertEquals("Banana", list.get(2));
		assertEquals("Orange", list.get(3));
		assertEquals(4, list.size());
		
		// Remove from front
		assertEquals("Apple", list.remove(0));
		assertEquals("Pear", list.get(0));
		assertEquals("Banana", list.get(1));
		assertEquals("Orange", list.get(2));
		assertEquals(3, list.size());
		
		// Remove from middle
		assertEquals("Banana", list.remove(1));
		assertEquals("Pear", list.get(0));
		assertEquals("Orange", list.get(1));
		assertEquals(2, list.size());
	}
}
