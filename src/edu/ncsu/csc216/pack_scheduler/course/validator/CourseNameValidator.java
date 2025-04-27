package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class implements the Course
 */
public class CourseNameValidator {
	/** keeps track of Letters in course name */
	private int letterCount;
	/** keeps track of Numbers in course name */
	private int digitCount;
	/** flag field for valid / invalid name */
	private boolean validEndState;
	/** CNV's current state */
	private State state;
	
	/** initial state field */
	private final State initialState = new CourseNameValidator.InititalState();
	/** letter state field */
	private final State letterState = new CourseNameValidator.LetterState();
	/** digit state field */
	private final State digitState = new CourseNameValidator.DigitState();
	/** suffix state field */
	private final State suffixState = new CourseNameValidator.SuffixState();
	
	/**
	 * Constructor for CourseNameValidator Class
	 */
	public CourseNameValidator() {
		state = initialState;
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
	}
	
	/**
	 * This method returns true if the parameter passed is a valid course name.
	 * 
	 * @param courseName the course name to be validated.
	 * @return true if the course name has 3-4 Letters, 3 Digits, and 0-1 Letter suffix
	 * @throws InvalidTransitionException if character is not a integer or a letter
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		int charIndex = 0;
		char c;
		
		// Look at constructor
		state = initialState;
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
		
		while (charIndex < courseName.length()) {
			c = courseName.charAt(charIndex);
			
			// Throw exception if char is not alphanumeric
			if(!Character.isLetter(c) && !Character.isDigit(c)) {
				this.state.onOther();
			}
			
			// behavior if char is a letter
			if (Character.isLetter(c)) {
				this.state.onLetter();
			}
			
			// behavior if char is a digit
			if (Character.isDigit(c)) {
				this.state.onDigit();
			}
			
			charIndex++;
		
		}
		
		return validEndState;
	}
	
	/**
	 * Abstract class for the state of the FSM.
	 * 
	 * Provides methods for behavior onLetter, onDigit, and onOther
	 * 
	 * @author Luke Early
	 */
	public abstract class State {
				
		/**
		 * Method encapsulating the behavior when the character input is a letter.
		 * 
		 * @throws InvalidTransitionException if an illegal state transition is attempted with a letter input
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Method encapsulating the behavior when the character input is a digit.
		 * 
		 * @throws InvalidTransitionException  if an illegal state transition is attempted with a digit input
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Method encapsulating the behavior when the character is anything other than a letter or a digit.
		 * 
		 * @throws InvalidTransitionException when character is not digit letter.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
		
	}
	
	/**
	 * Inner class for the initial state of the CNV
	 */
	public class InititalState extends State {

		/**
		 * Behavior of initial state if character is a letter.
		 */
		@Override
		public void onLetter() {
			state = letterState;
			letterCount++;
		}

		/**
		 * Behavior of initial state if input char is a digit

		 * @throws InvalidTransitionException if initial input is anything but a letter
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	}
	
	/**
	 * Inner class for the letter state of the CNV
	 * 
	 * @author Luke Early
	 */
	public class LetterState extends State {

		/** max amount of letters a course prefix may have */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/**
		 * Handles transitions from letter state when input is a letter.
		 * 
		 * @throws InvalidTransitionException if letterCount is 4 or greater.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				state = letterState;
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Handles transitions from letter state when input is digit
		 * 
		 * @throws InvalidTransitionException if letter count is less than 3, throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (letterCount >= 1) {
				state = digitState;
				digitCount++;
			}
			else {
				throw new InvalidTransitionException("Course name must start with a letter.");
			}
		}
	}
	
	/**
	 * Inner class for the digit state of the CNV
	 * 
	 * @author Luke Early
	 */
	public class DigitState extends State {

		/** max amount of numbers a course number may be */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/**
		 * Handles transition from digit state when input is a letter
		 * 
		 * @throws InvalidTransitionException if there are less than 3 digits and a letter is added
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				state = suffixState;
				validEndState = true;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Handles transition from digit state when input is a digit
		 * @throws InvalidTransitionException if a digit is passed when digitCount is greater than 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				state = digitState;
				digitCount++;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			}
		}
	}
	
	/**
	 * Inner class for the suffix state of the CNV
	 */
	public class SuffixState extends State {

		/**
		 * Handles transition from the suffix state when input is a letter
		 * 
		 * @throws InvalidTransitionException if a second letter is passed
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * Handles transition from the suffix state when the input is a digit
		 * 
		 * @throws InvalidTransitionException if a digit is passed
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
