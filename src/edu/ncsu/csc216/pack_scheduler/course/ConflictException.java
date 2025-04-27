package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Exception thrown by conflict interface when two or more events overlap.
 * @author Luke Early
 */
public class ConflictException extends Exception {

	/** Serial ID for ConflictException serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with a custom message parameter. Will print the String message when exception is 
	 * thrown.
	 * @param message message to be printed when exception is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor, will print "Schedule conflict".
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
