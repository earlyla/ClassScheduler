package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {
	/** LinkedQueue for testing */
	ArrayQueue<String> aQueue;
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
		aQueue = new ArrayQueue<String>(10);
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
		aQueue.enqueue(string1);
		assertEquals(1, aQueue.size());
	}

	/**
	 * tests removing one element from the queue
	 */
	@Test
	void testDequeueOneElement() {
		aQueue.enqueue(string1);
		assertEquals(1, aQueue.size());
		
		// remove element
		assertEquals(string1, aQueue.dequeue());
	}
	
	/**
	 * tests removing an element from a queue of size 0
	 */
	@Test
	void testDequeueFromEmptyQueue() {
		assertThrows(NoSuchElementException.class, 
				() -> aQueue.dequeue());
	}
	
	/**
	 * tests adding multiple elements
	 */
	@Test
	void testEnqueueMultipleElements() {
		aQueue.enqueue(string1);
		aQueue.enqueue(string2);
		aQueue.enqueue(string3);
		aQueue.enqueue(string4);
		aQueue.enqueue(string5);
		assertEquals(5, aQueue.size());
	}
	
	/**
	 * tests removing multiple elements
	 */
	@Test
	void testDequeueMultipleElements() {
		aQueue.enqueue(string1);
		aQueue.enqueue(string2);
		aQueue.enqueue(string3);
		aQueue.enqueue(string4);
		aQueue.enqueue(string5);
		assertEquals(5, aQueue.size());
		
		// remove element
		assertEquals(string1, aQueue.dequeue());
		assertEquals(string2, aQueue.dequeue());
		assertEquals(string3, aQueue.dequeue());
		assertEquals(string4, aQueue.dequeue());
		assertEquals(string5, aQueue.dequeue());
		assertEquals(0, aQueue.size());
	}
	
	/**
	 * tests add and remove
	 */
	@Test
	void testEnqueueDequeueMultiple() {
		// add 1, 2, 3
		aQueue.enqueue(string1);
		aQueue.enqueue(string2);
		aQueue.enqueue(string3);
		assertEquals(3, aQueue.size());
		
		// remove 1, 2, only 3 remains
		assertEquals(string1, aQueue.dequeue());
		assertEquals(string2, aQueue.dequeue());
		assertEquals(1, aQueue.size());
		
		// add 2, 1
		aQueue.enqueue(string2);
		aQueue.enqueue(string1);
		assertEquals(3, aQueue.size());
		
		// remove 3, 2, 1
		assertEquals(string3, aQueue.dequeue());
		assertEquals(string2, aQueue.dequeue());
		assertEquals(string1, aQueue.dequeue());
		assertTrue(aQueue.isEmpty());
		
	}

	/**
	 * tests isEmpty() method
	 */
	@Test
	void testIsEmpty() {
		assertTrue(aQueue.isEmpty());
	}

	/**
	 * tests the size() method
	 */
	@Test
	void testSize() {
		assertEquals(0, aQueue.size());
	}

	/**
	 * tests the setCapacity() method
	 */
	@Test
	void testSetCapacity() {
		// set cap to 10
		aQueue.setCapacity(10);
		
		// add 5 elements
		aQueue.enqueue(string1);
		aQueue.enqueue(string2);
		aQueue.enqueue(string3);
		aQueue.enqueue(string4);
		aQueue.enqueue(string5);
		assertEquals(5, aQueue.size());
		
		// set cap to 5, throw exception
		assertThrows(IllegalArgumentException.class, 
				() -> aQueue.setCapacity(4));
		
		// set cap to -1, throw exception
		assertThrows(IllegalArgumentException.class, 
				() -> aQueue.setCapacity(-1));
	}
}
