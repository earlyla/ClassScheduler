package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * This class creates a course roll of students
 * 
 * @author Luke Early
 */
public class CourseRoll {
	/** list of students in a course */
	private LinkedAbstractList<Student> roll;
	/** capacity of a roll */
	private int enrollmentCap;
	/** Smallest class size allowed */
	private static final int MIN_ENROLLMENT = 10;
	/** Largest class size allowed */
	private static final int MAX_ENROLLMENT = 250;
	/** max size of waitlist */
	private static final int WAITLIST_SIZE = 10;
	/** waitlist queue */
	private LinkedQueue<Student> waitlist;
	
	/**
	 * Creates a new CourseRoll object by initializing a LinkedAbstractList 
	 * and setting the enrollmentCap.
	 * 
	 * @param enrollmentCap max number of students allowed in course.
	 * @param c course that the CourseRoll is associated with
	 * @throws IllegalArgumentException if the course passed is null
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
		
		roll = new LinkedAbstractList<Student>(0);
		
		this.setEnrollmentCap(enrollmentCap);
		
	}
	
	/**
	 * Returns this roll's current max student enrollment number.
	 * 	
	 * @return number of students allowed to enroll in the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets this roll's current max student enrollment number.
	 * 	
	 * @param maxStudents the maximum number of students allowed to 
	 * 		  enroll in the course
	 * @throws IllegalArgumentException if maxStudents is less 
	 * 		   than MIN_ENROLLMENT or greater than MAX_ENROLLMENT.
	 * @throws IllegalArgumentException if new enrollmentCap is less
	 * 		   than the number of currently enrolled students.
	 * 		   
	 */
	public void setEnrollmentCap(int maxStudents) {
		if (maxStudents < MIN_ENROLLMENT || maxStudents > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		
		if (roll != null && maxStudents < roll.size()) {
			throw new IllegalArgumentException();
		}
		
		try {
			roll.setCapacity(maxStudents);
			this.enrollmentCap = maxStudents;
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Adds a student to the end of the roll.
	 * 
	 * @param s student to be added to the roll
	 * @throws IllegalArgumentException 1. student is null
	 * 		   2. roll.size() is equal to enrollmentCap
	 * 		   3. student is already in roll
	 * 		   4. If adding student generates an exception
	 * 		   5. If both the roll and waitlist are full
	 * 									
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (roll.size() == enrollmentCap && waitlist.size() == WAITLIST_SIZE) {
			throw new IllegalArgumentException();
		}
		
		try {
			if (roll.size() < enrollmentCap) {
				canEnroll(s);
				roll.add(roll.size(), s);
			} else {
				waitlist.enqueue(s);
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Finds a student on the roll and then removes that student.
	 * 
	 * @param s student to be removed from course
	 * @throws IllegalArgumentException if s is null
	 * @throws IllegalArgumentException if removing a student
	 * 		   generates an exception in LinkedAbstractList
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		try {
			if (roll.size() < enrollmentCap) {
				for (int i = 0; i < roll.size(); i++) {
					if (s.equals(roll.get(i))) {
						roll.remove(i);
					}
				}
				
			} else if (roll.size() == enrollmentCap && waitlist.isEmpty()) {
				for (int i = 0; i < roll.size(); i++) {
					if (s.equals(roll.get(i))) {
						roll.remove(i);
					}
				}
			} else if (roll.size() == enrollmentCap && !waitlist.isEmpty()) {
				for (int i = 0; i < roll.size(); i++) {
					if (s.equals(roll.get(i))) {
						roll.remove(i);
					}
				}
				
				Student waitlistStudentToAdd = waitlist.dequeue();
				
				roll.add(roll.size(), waitlistStudentToAdd);
			}
							
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
	} 
	
	/**
	 * Returns the number of open seats by finding the difference in
	 * the enrollmentCap and the current roll.size().
	 * 
	 * @return number of open seats left available
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * returns the number of students on the waitlist
	 * 
	 * @return number of students on waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**
	 * Returns true if a student is both not already enrolled
	 * and there are open seats.
	 * 
	 * @param s student to check enrollment eligibility
	 * @return true if student can enroll, false if not
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return waitlist.contains(s) || !waitlist.isEmpty();
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}
		
		return true;
	}
}
