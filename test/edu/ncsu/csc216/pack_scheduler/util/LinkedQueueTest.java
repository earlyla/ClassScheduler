package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedQueueTest {
	/** LinkedQueue for testing */
	LinkedQueue<String> lQueue;
	/** String element for testing */
	private String string1 = "str1";
	/** String element for testing */
	private String string2 = "str2";
	/** String element for testing */
	private String string3 = "str3";
	/** String element for testing */
	private String string4 = "str4";
	/** String element for testing */
	private String string5 = "str5";
	
	@BeforeEach
	void setUp() throws Exception {
		lQueue = new LinkedQueue<String>(10);
	}

	/**
	 * test constructor for LinkedQueue
	 */
	@Test
	void testConstructor() {
		LinkedQueue<String> testQueue = new LinkedQueue<String>(10);
		assertEquals(0, testQueue.size());
	}
	
	/**
	 * tests adding one element to the queue
	 */
	@Test
	void testEnqueueOneElement() {
		lQueue.enqueue(string1);
		assertEquals(1, lQueue.size());
	}

	/**
	 * tests removing one element from the queue
	 */
	@Test
	void testDequeueOneElement() {
		lQueue.enqueue(string1);
		assertEquals(1, lQueue.size());
		
		// remove element
		assertEquals(string1, lQueue.dequeue());
	}
	
	/**
	 * tests removing an element from a queue of size 0
	 */
	@Test
	void testDequeueFromEmptyQueue() {
		assertThrows(NoSuchElementException.class, 
				() -> lQueue.dequeue());
	}
	
	/**
	 * tests adding multiple elements
	 */
	@Test
	void testEnqueueMultipleElements() {
		lQueue.enqueue(string1);
		lQueue.enqueue(string2);
		lQueue.enqueue(string3);
		lQueue.enqueue(string4);
		lQueue.enqueue(string5);
		assertEquals(5, lQueue.size());
	}
	
	/**
	 * tests removing multiple elements
	 */
	@Test
	void testDequeueMultipleElements() {
		lQueue.enqueue(string1);
		lQueue.enqueue(string2);
		lQueue.enqueue(string3);
		lQueue.enqueue(string4);
		lQueue.enqueue(string5);
		assertEquals(5, lQueue.size());
		
		// remove element
		assertEquals(string1, lQueue.dequeue());
		assertEquals(string2, lQueue.dequeue());
		assertEquals(string3, lQueue.dequeue());
		assertEquals(string4, lQueue.dequeue());
		assertEquals(string5, lQueue.dequeue());
		assertEquals(0, lQueue.size());
	}
	
	/**
	 * tests add and remove
	 */
	@Test
	void testEnqueueDequeueMultiple() {
		// add 1, 2, 3
		lQueue.enqueue(string1);
		lQueue.enqueue(string2);
		lQueue.enqueue(string3);
		assertEquals(3, lQueue.size());
		
		// remove 1, 2, only 3 remains
		assertEquals(string1, lQueue.dequeue());
		assertEquals(string2, lQueue.dequeue());
		assertEquals(1, lQueue.size());
		
		// add 2, 1
		lQueue.enqueue(string2);
		lQueue.enqueue(string1);
		assertEquals(3, lQueue.size());
		assertTrue(lQueue.contains(string1));
		
		// remove 3, 2, 1
		assertEquals(string3, lQueue.dequeue());
		assertEquals(string2, lQueue.dequeue());
		assertEquals(string1, lQueue.dequeue());
		assertFalse(lQueue.contains(string1));
		assertTrue(lQueue.isEmpty());
		
	}

	/**
	 * tests isEmpty() method
	 */
	@Test
	void testIsEmpty() {
		assertTrue(lQueue.isEmpty());
	}

	/**
	 * tests the size() method
	 */
	@Test
	void testSize() {
		assertEquals(0, lQueue.size());
	}

	/**
	 * tests the setCapacity() method
	 */
	@Test
	void testSetCapacity() {
		// set cap to 10
		lQueue.setCapacity(10);
		
		// add 5 elements
		lQueue.enqueue(string1);
		lQueue.enqueue(string2);
		lQueue.enqueue(string3);
		lQueue.enqueue(string4);
		lQueue.enqueue(string5);
		assertEquals(5, lQueue.size());
		
		// set cap to 5, throw exception
		assertThrows(IllegalArgumentException.class, 
				() -> lQueue.setCapacity(4));
		
		// set cap to -1, throw exception
		assertThrows(IllegalArgumentException.class, 
				() -> lQueue.setCapacity(-1));
	}
}
