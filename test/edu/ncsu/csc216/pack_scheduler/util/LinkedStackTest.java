package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedStackTest {
	/** linkStack to test on */
	private LinkedStack<String> linkStack;
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
	/** linkStack initial capacity */
	private static final int STACK_CAP = 10;

	@BeforeEach
	void setUp() throws Exception {
		linkStack = new LinkedStack<String>(STACK_CAP);
	}
	
	/**
	 * Tests constructor for Stack
	 */
	@Test
	void testArrayStack() {
		assertEquals(0, linkStack.size());
		
		assertTrue(linkStack.isEmpty());
	}

	/**
	 * test push() to an empty Stack
	 */
	@Test
	void testPushEmpty() {
		assertEquals(0, linkStack.size());
		linkStack.push(testString1);
		
		assertEquals(1, linkStack.size());
	}

	/**
	 * test pop() from an empty Stack
	 */
	@Test
	void testPopEmpty() {
		assertEquals(0, linkStack.size());
		
		assertThrows(EmptyStackException.class,
				() -> linkStack.pop());
		
	}
	
	/**
	 * tests push() for multiple elements
	 */
	@Test
	void testPushMultiple() {
		assertEquals(0, linkStack.size());
		
		linkStack.push(testString1);
		linkStack.push(testString2);
		
		assertEquals(2, linkStack.size());
	}

	/**
	 * tests pop() for multiple elements
	 */
	@Test
	void testPopMultiple() {
		assertEquals(0, linkStack.size());
		
		linkStack.push(testString1);
		linkStack.push(testString2);
		
		assertEquals(2, linkStack.size());
		
		assertEquals(testString2, linkStack.pop());
		assertEquals(testString1, linkStack.pop());
		
		assertEquals(0, linkStack.size());
	}
	
	/**
	 * tests push() and pop() for multiple elements
	 */
	@Test
	void testPushAndPopMultiple() {
		assertEquals(0, linkStack.size());
		
		linkStack.push(testString1);
		assertEquals(1, linkStack.size());
		assertEquals(testString1, linkStack.pop());
		assertEquals(0, linkStack.size());
		
		linkStack.push(testString1);
		linkStack.push(testString2);
		linkStack.push(testString3);
		
		assertEquals(3, linkStack.size());
		assertEquals(testString3, linkStack.pop());
		assertEquals(testString2, linkStack.pop());
		assertEquals(1, linkStack.size());
		
		linkStack.push(testString4);
		linkStack.push(testString5);
		
		assertEquals(3, linkStack.size());		
	}
	
	/**
	 * tests pushing one item too many
	 */
	@Test
	void testPushPastCap() {
		linkStack.push(testString1);
		linkStack.push(testString2);
		linkStack.push(testString3);
		linkStack.push(testString4);
		linkStack.push(testString5);
		linkStack.push("six");
		linkStack.push("seven");
		linkStack.push("eight");
		linkStack.push("nine");
		linkStack.push("ten");
		
		assertEquals(10, linkStack.size());
		
		assertThrows(IllegalArgumentException.class,
				() -> linkStack.push("eleven"));
		
	}

	/**
	 * tests isEmpty()
	 */
	@Test
	void testIsEmpty() {
		LinkedStack<String> nullStack = null;
		assertThrows(NullPointerException.class,
				() -> nullStack.isEmpty());
		
		// ensure new list is empty
		assertTrue(linkStack.isEmpty());
		
		// push elements
		linkStack.push(testString1);
		linkStack.push(testString2);
		assertFalse(linkStack.isEmpty());
		
		// pop elements
		linkStack.pop();
		assertFalse(linkStack.isEmpty());
		
		// pop remaining elements
		linkStack.pop();
		assertTrue(linkStack.isEmpty());
	}

	/**
	 * tests size();
	 */
	@Test
	void testSize() {
		// ensure stack is empty
		assertEquals(0, linkStack.size());
		
		// add 1 element
		linkStack.push(testString1);
		assertEquals(1, linkStack.size());
		
		// push multiple elements
		linkStack.push(testString2);
		linkStack.push(testString3);
		linkStack.push(testString4);
		assertEquals(4, linkStack.size());
		
		// remove elements
		linkStack.pop();
		linkStack.pop();
		assertEquals(2, linkStack.size());
	}

	/**
	 * test setCapacity()
	 */
	@Test
	void testSetCapacity() {
		// exceptions
		assertThrows(IllegalArgumentException.class,
				() -> linkStack.setCapacity(-1));
		
		// push elements
		linkStack.push(testString1);
		linkStack.push(testString2);
		linkStack.push(testString3);
		linkStack.push(testString4);
		
		// attempt to set cap to 3
		assertThrows(IllegalArgumentException.class,
				() -> linkStack.setCapacity(3));
		
		// attempt to set cap to size()
		assertThrows(IllegalArgumentException.class,
				() -> linkStack.setCapacity(linkStack.size() - 1));
		
	}

}
