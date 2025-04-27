package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Exception thrown when transition between states is illegal.
 * 
 * @author Luke Early
 */
public class InvalidTransitionException extends Exception {

	/** Serial ID for ConflictException serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with a custom message parameter. Will print the String message when exception is 
	 * thrown.
	 * @param message message to be printed when exception is thrown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor, will print "Invalid FSM Transition.".
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

}
