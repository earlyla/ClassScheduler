package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class represents a complete student record.
 * 
 * The class has 6 fields:
 * 		firstName: student's first name (cannot be null / empty)
 * 		lastName: student's last name (cannot be null / empty)
 * 		id: student's id (cannot be null / empty)
 * 		email: student's email (cannot be null / empty, must have an '@' and a '.', but
 * 			   the final '.' cannot come prior to the '@')
 * 		password: student's password (cannot be null / empty)
 * 		maxCredit: max number of credits (cannot be null / empty, more than 18 or less than 3)
 * 
 * There are getters and setters for each field, a hashCode method, an equals method to compare
 * objects, and a toString() method overridden for purposes of record keeping.
 * 
 * @author Luke Early
 * 
 */
public class Student extends User implements Comparable<Student> {
	/** max number of credits THIS student can take */
	private int maxCredits;
	/** student schedule */
	private Schedule schedule = new Schedule();
	/** max number of credits any student can take */
	public static final int MAX_CREDITS = 18;

	/**
	 * Constructor for student object
	 * 
	 * @param firstName first name of student.
	 * @param lastName last name of student
	 * @param id unique id of student
	 * @param email email of student
	 * @param password hashed PW of student
	 * @param maxCredits max number of credits constructed student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * Alternate constructor for student object
	 * 
	 * @param firstName first name of student.
	 * @param lastName last name of student
	 * @param id unique id of student
	 * @param email email of student
	 * @param password hashed PW of student
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, 18);
	}
	
	/**
	 * Sets the maxCredits a student can take.
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if maxCredits is more than 18 or less than 3
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Returns the maxCredits field
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Overrides toString method, prints out student record in the following format:
	 * 
	 * 		firstName,lastName,studentId,studentEmail,studentPassword,studentCredits
	 * 
	 * @return s string formatted specifically for writing to .txt file
	 */
	@Override
	public String toString() {
		String s = getFirstName() + ",";
		s += getLastName() + ",";
		s += getId() + ",";
		s += getEmail() + ",";
		s += getPassword() + ",";
		s += getMaxCredits();
				
		return s;
	}

	/**
	 * Compares students last, first and unityId to place them on list according to alphabetical
	 * order.
	 * 
	 * @param s student object to be compared to
	 * @return -1 if object is ahead of alphabetical order, 0 if they are the same, 
	 * 			1 if object is behind in alphabetical order
	 */
	@Override
	public int compareTo(Student s) {
	
		int lastNameCompare = this.getLastName().compareTo(s.getLastName());
		int firstNameCompare = this.getFirstName().compareTo(s.getFirstName());
		int unityIdCompare = this.getId().compareTo(s.getId());
		
		if (lastNameCompare != 0) {
			if (lastNameCompare < 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (firstNameCompare != 0) {
			if (firstNameCompare < 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (unityIdCompare != 0) {
			if (unityIdCompare < 0) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Generates hash code for User Object.
	 * 
	 * @return result an integer representing the hashCode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Returns true if object compared to is the same as object being compared, false if not.
	 * 
	 * @return true if objects are the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Returns the students schedule
	 * 
	 * @return schedule the schedule for the student
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Returns true if the course can be added to the students schedule, false if course is null, 
	 * already present, there is a conflict, or the student has no more room in their schedule.
	 * 
	 * @param c course to check schedule-ability
	 * @return true if c can be added, false if it can't
	 */
	public boolean canAdd(Course c) {
		if (!schedule.canAdd(c)) {
			return false;
		}
		
		if (schedule.getScheduleCredits() + c.getCredits() > maxCredits) {
			return false;
		}
		
		return true;
	}
}
