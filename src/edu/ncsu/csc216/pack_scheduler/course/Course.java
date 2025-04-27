package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This class is a child of and extends Activity.java
 * 
 * This class creates a Course object with private fields consisting of name, title, section, 
 * credits, instructorId, meetingDays, startTime, and endTime.
 * 
 * Class includes overwritten equals(), hashCode(), and toString() methods, as well as getters
 * and setters for all fields.
 * 
 * @author Luke Early
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's list of students */
	private CourseRoll courseRoll;
	/** Minimum number of char in course name */
	public static final int MIN_NAME_LENGTH = 4;
	/** Minimum number of char in course name */
	public static final int MAX_NAME_LENGTH = 8;
	/** Minimum number of letters in course name */
	public static final int MIN_LETTER_COUNT = 1;
	/** Maximum number of letters in course name */
	public static final int MAX_LETTER_COUNT = 4;
	/** Number of digits course name must have */
	public static final int DIGIT_COUNT = 3;
	/** Length of section */
	public static final int SECTION_LENGTH = 3;
	/** Minimum number of credits in course */
	public static final int MIN_CREDITS = 1;
	/** Minimum number of credits in course */
	public static final int MAX_CREDITS = 5;
	
	/**
	 * Returns the courses name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the courses name.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is < 5 || > 8 char, name == null, doesn't follow
	 * 									L[LLL] NNN construction, letters is > 4, or digits != 3.
	 */
	private void setName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		try {
			CourseNameValidator validator = new CourseNameValidator();
			if (!validator.isValid(name)) {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;
		
	}
	
	/**
	 * Returns the courses section. 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the courses section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section null or empty, if length is not 3,
	 * 									or if any of the section is not a letter 
	 */
	public void setSection(String section) {
		if (section == null || "".equals(section)) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for (int i = 0; i < section.length(); i++) {
			char chSection = section.charAt(i);
			if (!Character.isDigit(chSection)) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		
		this.section = section;
	}
	
	/**
	 * Returns the courses credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the courses credits.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits greater than MAX_CREDITS or less than MIN_CREDITS
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		if (credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.credits = credits;
	}
	
	/**
	 * Returns the courses instructor ID.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/** 
	 * Sets the courses instructor ID.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if param is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		this.instructorId = instructorId;
	}
	
	/**
	 * Creates a course with all fields.
	 * 
	 * @param name name of course object
	 * @param title course title
	 * @param section section of course object
	 * @param credits credits of course object
	 * @param instructorId instructorId for course object
	 * @param enrollmentCap max number of students allowed in the course
	 * @param meetingDays course meeting days
	 * @param startTime course start time
	 * @param endTime course end time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    courseRoll = new CourseRoll(this, enrollmentCap);
	}
	
	/**
	 * Creates a course with a given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of course object
	 * @param title title of course object
	 * @param section section of course object
	 * @param credits credits of course object
	 * @param instructorId instructorId for course object
	 * @param enrollmentCap max number of students allowed in the course
	 * @param meetingDays meeting days for course object
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
		
	/**
	 * Sets the meetingDays, startTime and endTime for a course.
	 * 
	 * @throws IllegalArgumentException "Invalid meeting days and times." if: 
	 * 									- startTime or endTime are not 0 when meetingDays is "A"
	 * 									- a day other than M,T,W,H,F is entered
	 * 									- more than one M,T,W,H,F is entered
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);

		} else {
			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;
			
			for (int i = 0; i < meetingDays.length(); i++) {
				char dayChar = meetingDays.charAt(i);
				if (dayChar == 'M') {
					countM++;
				}
				else if (dayChar == 'T') {
					countT++;
				}
				else if (dayChar == 'W') {
					countW++;
				}
				else if (dayChar == 'H') {
					countH++;
				}
				else if (dayChar == 'F') {
					countF++;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
			
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + courseRoll.getOpenSeats() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + courseRoll.getOpenSeats() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
	/**
	 * Generates hashCode for a Course object based on credits, instructorId, name, section
	 * 
	 * @return result an int representing the objects hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Determines if two objects are equal by comparing their fields and other aspects.
	 * 
	 * @return true if the two objects are equal and false if they aren't
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 *  returns an array of length 4 containing 
	 *  the Course name, section, title, and meeting string.
	 *  
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[5];
		
		shortDisplayArray[0] = getName();
		shortDisplayArray[1] = getSection();
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();	
		shortDisplayArray[4] = courseRoll.getOpenSeats() + "";
		
		return shortDisplayArray;
	}
	
	/**
	 *  returns an array of length 7 containing the Course name, 
	 *  section, title, credits, instructorId, meeting string, empty 
	 *  string (for a field that Event will have that Course does not).
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		
		longDisplayArray[0] = getName();
		longDisplayArray[1] = getSection();
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = getCredits() + "";
		longDisplayArray[4] = getInstructorId();
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = "";
		
		return longDisplayArray;
	}
	
	/**
	 * Determines if one Activity object is a duplicate based on name
	 * 
	 * @return true if Activity is a duplicate, false if it is not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course other = (Course) activity;
			if (getName().equals(other.getName())) {
				return true;
			}
		} else {
			return false;
		} 
		
		return false;
		
		
	}
	
	/**
	 * Returns this course's list of students.
	 * 
	 * @return courseRoll the list of students enrolled in this course.
	 */
	public CourseRoll getCourseRoll() {
		return courseRoll;
	}
	
	/**
	 * Compares courses to each other based on name, then section.
	 * 
	 * If a course's name is before another (CSC 216 is before CSC 226 lexicographically) then returns -1.
	 * If a course is after (CSC 226 is after CSC 216), returns 1. If names are the same, moves on to check 
	 * section where the same checks occur for sections.
	 * 
	 * If all are equal, 0 is returned.
	 * 
	 * @return int representing which comes first. -1 if course comes first, 0 if the same course, 1 if 
	 * 		   course comes after.
	 * 
	 */
	@Override
	public int compareTo(Course c) {
		int courseNameCompareNum = this.getName().compareTo(c.getName());
		int courseSectionCompareNum = this.getSection().compareTo(c.getSection());
		
		if (courseNameCompareNum != 0) {
			if (courseNameCompareNum < 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (courseSectionCompareNum != 0) {
			if (courseSectionCompareNum < 0) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}
}
