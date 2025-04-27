package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests ConflictException Class, both default constructor and custom message constructor
 * @author Luke Early
 */
class ConflictExceptionTest {
	/**
	 * Test method for ConflictException(String message).
	 */
	@Test
	void testConflictExceptionCustomMessage() {
		ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for ConflictException()
	 */
	@Test
	void testConflictExceptionDefault() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}