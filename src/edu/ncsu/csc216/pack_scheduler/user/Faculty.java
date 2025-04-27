package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty user object
 */
public class Faculty extends User {
	/** max number of courses an instructor can teach */
	private int maxCourses;
	/** constant for the minimum number of courses an individual may teach */
	public static final int MIN_COURSES = 1;
	/** constant for the max number of courses an individual may teach */
	public static final int MAX_COURSES = 3;
	/** faculty schedule */
	private FacultySchedule schedule;
	
	/**
	 * Faculty object constructor. Initializes course schedule for faculty member as well.
	 * 
	 * @param firstName fn of faculty
	 * @param lastName ln of faculty
	 * @param id of faculty
	 * @param email of faculty
	 * @param hashedPassword of faculty
	 * @param maxCourses faculty mem can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashedPassword, int maxCourses) {
		super(firstName, lastName, id, email, hashedPassword);
		
		setMaxCourses(maxCourses);
		
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * Returns faculty schedule
	 * 
	 * @return the schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * True if schedule has more than maxCourse, false if not
	 * 
	 * @return boolean true if schedule has more courses than faculty's maxCourses
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}

	/**
	 * Returns max courses faculty can teach
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * sets max courses facutly can teach
	 * 
	 * @param maxCourses the maxCourses to set
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses > MAX_COURSES || maxCourses < MIN_COURSES) {
			throw new IllegalArgumentException();
		}
		
		this.maxCourses = maxCourses;
	}

	/**
	 * Overidden hash code for faculty object
	 * 
	 * @return hashCode of object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Overridden equals method for faculty
	 * 
	 * @return true if this object is the same as passed object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Faculty))
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Overridden toString returns string representation of faculty object
	 * 
	 * @return string representing all of faculty objects state
	 */
	@Override
	public String toString() {
		String s = "";
		
		s += getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ",";
		s += getMaxCourses() + "";
		
		return s;
	}
	
}
