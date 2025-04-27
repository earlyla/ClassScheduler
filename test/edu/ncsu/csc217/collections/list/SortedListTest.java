package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the SortedList class and its methods
 * @author Luke Early
 */
public class SortedListTest {

	/**
	 * Tests creation / expansion of new SortedList object
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// adding 10 items to the list
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		
		assertEquals(10, list.size());
		
		// adding 11th item to ensure list resizes
		list.add("k");		
		assertEquals(11, list.size());
		
	}

	/**
	 * Tests add() method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// add element to beginning (before banana)
		list.add("aardvark");
		assertEquals("aardvark", list.get(0));
		
		// add element to end (after banana)
		list.add("xylophone");
		assertEquals(2, list.indexOf("xylophone"));
		
		// add element to middle (between banana and xylophone)
		list.add("laptop");
		assertEquals(2, list.indexOf("laptop"));
		
		// Test adding a null element
		assertThrows(NullPointerException.class, 
						() -> list.add(null));
		assertEquals(4, list.size());
		
		// Test adding a duplicate element
		assertThrows(IllegalArgumentException.class,
						() -> list.add("laptop"));
		assertEquals(4, list.size());
	}
	
	/**
	 * tests get() method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		// Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
						() -> list.get(0));
		
		// Add some elements to the list
		list.add("a1");
		list.add("b2");
		list.add("c3");
		
		// Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		
		// Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
		
	}
	
	/**
	 * Tests remove() method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		// Add some elements to the list - at least 4
		list.add("a0");
		list.add("b1");
		list.add("c2");
		list.add("d3");
		list.add("e4");
		list.add("f5");
		
		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(list.size()));
		
		// Test removing a middle element
		assertEquals("c2", list.get(2));
		list.remove(2);
		assertEquals("d3", list.get(2));		
		
		// Test removing the last element
		assertEquals("f5", list.get(list.size() - 1));
		list.remove(list.size() - 1);
		assertEquals("e4", list.get(list.size() - 1));
		
		// Test removing the first element
		assertEquals("a0", list.get(0));
		list.remove(0);
		assertEquals("b1", list.get(0));		
		
		// Test removing the last element
		assertEquals("e4", list.get(list.size() - 1));
		list.remove(list.size() - 1);
		assertEquals("d3", list.get(list.size() - 1));
		
	}
	
	/**
	 * tests indexOf() method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("a0"));
		
		// Add some elements
		list.add("a0");
		list.add("b1");
		list.add("c2");
		list.add("d3");
		list.add("e4");
		
		// Test .indexOf() for first, middle, last element
		assertEquals(0, list.indexOf("a0"));
		assertEquals(2, list.indexOf("c2"));
		assertEquals(list.size() - 1, list.indexOf("e4"));
		
		// Test checking the index of null
		assertThrows(NullPointerException.class,
						() -> list.indexOf(null));
		
	}
	
	/**
	 * tests clear() method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("a0");
		list.add("b1");
		list.add("c2");
		list.add("d3");
		list.add("e4");
		
		// Test list has 5 elements, clears, tests it's empty
		assertEquals(5, list.size());
		list.clear();
		assertEquals(0, list.size());
		
	}

	/**
	 * tests isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		assertTrue(list.isEmpty());
		
		// Add at least one element
		list.add("a0");
		list.add("b1");
		list.add("c2");
		
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * tests contains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("a1"));
		
		// Add some elements
		list.add("a0");
		list.add("b1");
		list.add("c2");
		list.add("d3");
		list.add("e4");
		
		// Test some true and false cases
		assertTrue(list.contains("a0"));
		assertTrue(list.contains("c2"));
		assertTrue(list.contains("e4"));
		
		assertFalse(list.contains("bb1"));
		assertFalse(list.contains("b11"));
		assertFalse(list.contains("1b"));
		
	}
	
	/**
	 * tests equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a0");
		list1.add("b1");
		list1.add("c2");
		
		list2.add("a0");
		list2.add("b1");
		list2.add("c2");
		
		list3.add("a0");
		list3.add("b1");
		list3.add("c2");
		list3.add("d3");
		list3.add("e4");
		
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		
	}
	
	/**
	 * tests hashCode() method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a0");
		list1.add("b1");
		list1.add("c2");
		
		list2.add("a0");
		list2.add("b1");
		list2.add("c2");
		
		list3.add("a0");
		list3.add("b1");
		list3.add("c2");
		list3.add("d3");
		list3.add("e4");
		
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());	
	}
}
 
