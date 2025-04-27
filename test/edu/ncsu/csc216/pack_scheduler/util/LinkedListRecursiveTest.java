package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListRecursiveTest {
	/** recurList to test on */
	LinkedListRecursive<String> testList;
	
	@BeforeEach
	void setUp() throws Exception {
		testList = new LinkedListRecursive<String>();
	}

	@Test
	void testConstructor() {
		LinkedListRecursive<String> recurList = new LinkedListRecursive<String>();
		assertEquals(0, recurList.size());
		
		assertTrue(recurList.isEmpty());
	}
	
	@Test
	void testAddAndContains() {
		testList.add("a");
		assertEquals(1, testList.size());
		assertTrue(testList.contains("a"));
		
		testList.add("b");
		assertEquals(2, testList.size());
		assertTrue(testList.contains("a"));
		assertTrue(testList.contains("b"));
		
		testList.add("c");
		assertEquals(3, testList.size());
		assertTrue(testList.contains("a"));
		assertTrue(testList.contains("b"));
		assertTrue(testList.contains("c"));
		
		// exception testing
		assertThrows(IllegalArgumentException.class, 
				() -> testList.add("a"));
	}
	
	@Test
	void testAddAtIndex() {
		testList.add("a");
		testList.add("c");
		testList.add("d");
		assertEquals(3, testList.size());
		
		testList.add(1, "b");
		assertEquals(4, testList.size());
		assertTrue(testList.contains("b"));
		
		// add to back
		testList.add(testList.size() - 1, "e");
		assertEquals(5, testList.size());
		assertTrue(testList.contains("e"));
		
		// add to front
		testList.add(0, "start");
		assertEquals(6, testList.size());
		assertTrue(testList.contains("start"));
		
		// exception testing
		assertThrows(IllegalArgumentException.class, 
				() -> testList.add(2, "a"));
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.add(-1, "q"));
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.add(testList.size() + 1, "q"));
		
		assertThrows(NullPointerException.class, 
				() -> testList.add(2, null));
		
	}
	
	@Test
	void testGet() {
		String[] contents = {"a", "b", "c", "d"};
		
		for (int i = 0; i < 4; i++) {
			testList.add(contents[i]);
		}
		assertEquals(4, testList.size());
		
		for (int i = 0; i < 4; i++) {
			String currString = testList.get(i);
			assertEquals(contents[i], currString);
		}
		
		// IIOBE
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.get(4));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.get(-1));

	}
	
	@Test
	void testRemoveInt() {
		String[] contents = {"a", "b", "c", "d"};
		
		for (int i = 0; i < 4; i++) {
			testList.add(contents[i]);
		}
		assertEquals(4, testList.size());
		
		// remove from beginning
		assertEquals("a", testList.remove(0));
		assertEquals(3, testList.size());
		
		// remove from middle
		assertEquals("c", testList.remove(1));
		assertEquals(2, testList.size());
		
		// remove from end
		assertEquals("d", testList.remove(1));
		assertEquals(1, testList.size());
		
		// remove from empty list
		assertEquals("b", testList.remove(0));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.remove(-1));
		
		// IIOBE
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.remove(4));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.remove(-1));
	}
	
	@Test
	void testRemoveElement() {
		String[] contents = {"a", "b", "c", "d"};
		
		for (int i = 0; i < 4; i++) {
			testList.add(contents[i]);
		}
		assertEquals(4, testList.size());
		
		// remove from beginning
		assertTrue(testList.remove("a"));
		assertEquals(3, testList.size());
		
		// remove from middle
		assertTrue(testList.remove("c"));
		assertEquals(2, testList.size());
		
		// remove from end
		assertTrue(testList.remove("d"));
		assertEquals(1, testList.size());
		
		// exception testing	
		assertFalse(testList.remove(null));
		
		// create new empty list
		LinkedListRecursive<String> emptyList = new LinkedListRecursive<String>();
		
		// remove item, throw exception
		assertFalse(emptyList.remove("a"));
	}
	
	@Test
	void testSetElement() {
		String[] contents = {"a", "b", "c", "d"};
		for (int i = 0; i < 4; i++) {
			testList.add(contents[i]);
		}
		assertEquals(4, testList.size());
		
		// set at beginning
		assertEquals("a", testList.set(0, "start"));
		assertEquals(4, testList.size());
		
		// set at middle
		assertEquals("c", testList.set(2, "3"));
		assertEquals(4, testList.size());
		
		// set at end
		assertEquals("d", testList.set(3, "end"));
		assertEquals(4, testList.size());
	}
	
	@Test
	void testNPE() {
		String[] contents = {"a", "b", "c", "d"};
		for (int i = 0; i < 4; i++) {
			testList.add(contents[i]);
		}
		assertEquals(4, testList.size());
		/* figure out why NPE throwing for each of the following */
		// test get int
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.get(-1));
		assertEquals("a", testList.get(0));
		assertEquals("b", testList.get(1));
		assertEquals("c", testList.get(2));
		assertEquals("d", testList.get(3));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> testList.get(4));
		
		// test remove int
		
		// test remove E
		
		// test set int E
		
	}
}
