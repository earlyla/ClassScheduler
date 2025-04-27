package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayStackTest {
	/** arrStack to test on */
	private ArrayStack<String> arrStack;
	/** Test String 1 */
	private String testString1 = "ts 1";
	/** Test String 2 */
	private String testString2 = "ts 2";
	/** Test String 3 */
	private String testString3 = "ts 3";
	/** Test String 4 */
	private String testString4 = "ts 4";
	/** Test String 5 */
	private String testString5 = "ts 5";
	/** arrStack initial capacity */
	private static final int STACK_CAP = 10;

	@BeforeEach
	void setUp() throws Exception {
		arrStack = new ArrayStack<String>(STACK_CAP);
	}
	
	/**
	 * Tests constructor for Stack
	 */
	@Test
	void testArrayStack() {
		assertEquals(0, arrStack.size());
		
		assertTrue(arrStack.isEmpty());
	}

	/**
	 * test push() to an empty Stack
	 */
	@Test
	void testPushEmpty() {
		assertEquals(0, arrStack.size());
		arrStack.push(testString1);
		
		assertEquals(1, arrStack.size());
	}

	/**
	 * test pop() from an empty Stack
	 */
	@Test
	void testPopEmpty() {
		assertEquals(0, arrStack.size());
		
		assertThrows(EmptyStackException.class,
				() -> arrStack.pop());
		
	}
	
	/**
	 * tests push() for multiple elements
	 */
	@Test
	void testPushMultiple() {
		assertEquals(0, arrStack.size());
		
		arrStack.push(testString1);
		arrStack.push(testString2);
		
		assertEquals(2, arrStack.size());
	}

	/**
	 * tests pop() for multiple elements
	 */
	@Test
	void testPopMultiple() {
		assertEquals(0, arrStack.size());
		
		arrStack.push(testString1);
		arrStack.push(testString2);
		
		assertEquals(2, arrStack.size());
		
		assertEquals(testString2, arrStack.pop());
		assertEquals(testString1, arrStack.pop());
		
		assertEquals(0, arrStack.size());
	}
	
	/**
	 * tests push() and pop() for multiple elements
	 */
	@Test
	void testPushAndPopMultiple() {
		assertEquals(0, arrStack.size());
		
		arrStack.push(testString1);
		assertEquals(1, arrStack.size());
		assertEquals(testString1, arrStack.pop());
		assertEquals(0, arrStack.size());
		
		arrStack.push(testString1);
		arrStack.push(testString2);
		arrStack.push(testString3);
		
		assertEquals(3, arrStack.size());
		assertEquals(testString3, arrStack.pop());
		assertEquals(testString2, arrStack.pop());
		assertEquals(1, arrStack.size());
		
		arrStack.push(testString4);
		arrStack.push(testString5);
		
		assertEquals(3, arrStack.size());		
	}

	/**
	 * tests isEmpty()
	 */
	@Test
	void testIsEmpty() {
		// ensure new list is empty
		assertTrue(arrStack.isEmpty());
		
		// push elements
		arrStack.push(testString1);
		arrStack.push(testString2);
		assertFalse(arrStack.isEmpty());
		
		// pop elements
		arrStack.pop();
		assertFalse(arrStack.isEmpty());
		
		// pop remaining elements
		arrStack.pop();
		assertTrue(arrStack.isEmpty());
	}
	
	/**
	 * tests pushing one item too many
	 */
	@Test
	void testPushPastCap() {
		arrStack.push(testString1);
		arrStack.push(testString2);
		arrStack.push(testString3);
		arrStack.push(testString4);
		arrStack.push(testString5);
		arrStack.push("six");
		arrStack.push("seven");
		arrStack.push("eight");
		arrStack.push("nine");
		arrStack.push("ten");
		
		assertEquals(10, arrStack.size());
		
		assertThrows(IllegalArgumentException.class,
				() -> arrStack.push("eleven"));
		
	}

	/**
	 * tests size();
	 */
	@Test
	void testSize() {
		// ensure stack is empty
		assertEquals(0, arrStack.size());
		
		// add 1 element
		arrStack.push(testString1);
		assertEquals(1, arrStack.size());
		
		// push multiple elements
		arrStack.push(testString2);
		arrStack.push(testString3);
		arrStack.push(testString4);
		assertEquals(4, arrStack.size());
		
		// remove elements
		arrStack.pop();
		arrStack.pop();
		assertEquals(2, arrStack.size());
	}

	/**
	 * test setCapacity()
	 */
	@Test
	void testSetCapacity() {
		// exceptions
		assertThrows(IllegalArgumentException.class,
				() -> arrStack.setCapacity(-1));
		
		// push elements
		arrStack.push(testString1);
		arrStack.push(testString2);
		arrStack.push(testString3);
		arrStack.push(testString4);
		
		// attempt to set cap to 3
		assertThrows(IllegalArgumentException.class,
				() -> arrStack.setCapacity(3));
		
		// attempt to set cap to size()
		assertThrows(IllegalArgumentException.class,
				() -> arrStack.setCapacity(arrStack.size() - 1));
		
	}

}
