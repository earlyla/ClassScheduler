package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * LinkedList w/ Iterator test class
 */
class LinkedListTest {
	/** LinkedList to test */
	LinkedList<String> listTest;
	/** array of strings to test with */
	private String[] stringArray = {"a", "b", "c", "d", "e", "f", "g", "h"};
	
	@BeforeEach
	void setUp() throws Exception {
		listTest = new LinkedList<String>();
	}

	@Test
	void testLinkedListConstructor() {
		LinkedList<String> constructionTest = new LinkedList<String>();
		assertEquals(0, constructionTest.size());
		
	}
	
	@Test
	void testHasNext() {
		assertFalse(listTest.listIterator(0).hasNext());
	}
	
	@Test
	void testHasPrevious() {
		assertFalse(listTest.listIterator(0).hasPrevious());
	}
	
	@Test
	void testNextIndex() {
		assertEquals(0, listTest.listIterator(0).nextIndex());
	}
	
	@Test
	void testPreviousIndex() {
		assertEquals(-1, listTest.listIterator(0).previousIndex());
	}
	
	@Test
	void testNext() {
		assertThrows(NoSuchElementException.class,
				() -> listTest.listIterator(0).next());
	}
	
	@Test
	void testPrevious() {
		assertThrows(NoSuchElementException.class,
				() -> listTest.listIterator(0).previous());
	}
	
	@Test
	void testAddSetRemove() {
		// adds a bunch of strings to the LinkedList
		for (int i = 0; i < stringArray.length; i++) {
			listTest.add(i, stringArray[i]);
		}
		
		assertEquals(8, listTest.size());
		
		for (int i = 0; i < stringArray.length; i++) {
			assertEquals(stringArray[i], listTest.get(i));
		}
		
		// add some stuff, ensure it was added
		listTest.add(0, "start");
		assertEquals("start", listTest.get(0));
		listTest.add(3, "the");
		assertEquals("the", listTest.get(3));
		listTest.add(4, "moose");
		assertEquals("moose", listTest.get(4));
		listTest.add(listTest.size(), "end");
		assertEquals("end", listTest.get(listTest.size() - 1));
		
		// test set exceptions
		assertThrows(IllegalArgumentException.class,
				() -> listTest.set(1, "a"));
		
		listTest.set(5, "hope this works");
	
		// test set
		ListIterator<String> testItr1 = listTest.listIterator(1);
		testItr1.next();
		testItr1.set("monkey");
		
		ListIterator<String> testItr2 = listTest.listIterator(2);
		testItr2.previous();
		testItr2.previous();
		testItr2.remove();
		assertEquals("monkey", listTest.get(0));
	}	
}
