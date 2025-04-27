package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Tests InvalidTransitionException to ensure functionality
 * 
 * @author Luke Early
 */
class InvalidTransitionExceptionTest {

	/**
	 * Tests default exception
	 */
	@Test
	void testDefaultException() {
		InvalidTransitionException ite = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ite.getMessage());
	}
	
	/**
	 * Tests custom exception
	 */
	@Test
	void testCustomException() {
		InvalidTransitionException ite = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ite.getMessage());
	}
}
