package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * This class creates a Schedule object for a student.
 * 
 * @author Luke Early
 */
public class Schedule {
	/** ArrayList of courses */
	ArrayList<Course> schedule;
	/** the title of a schedule */
	String title;
	/** Number of individual data stored in each column of getScheduledCourses() */
	private static final int COURSE_INFO_INT = 5;
	
	/**
	 * Schedule constructor, creates an empty ArrayList of courses and sets the title to "My Schedule"
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		
		title = "My Schedule";
	}
	
	/**
	 * Adds a course to the schedule if the course is not null, there are no schedule conflicts, and 
	 * the course is not already on the schedule.
	 * 
	 * @param courseToAdd the course to be added to the schedule
	 * @return isAdded the boolean flag which is true if added, and false otherwise
	 * @throws IllegalArgumentException if the course is a duplicate
	 * @throws IllegalArgumentException if the course conflicts with another
	 */
	public boolean addCourseToSchedule(Course courseToAdd) {
		boolean isAdded = false;
		
		// pre-conditions
		if (courseToAdd == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < schedule.size(); i++) {
			if (courseToAdd.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + courseToAdd.getName());
			}
		}
		
		try {
			for (int i = 0; i < schedule.size(); i++) {
				courseToAdd.checkConflict(schedule.get(i));
			}
		} catch (ConflictException e) {
			throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		}
		
		schedule.add(courseToAdd);
		isAdded = true;
		
		return isAdded;
	}
	
	/**
	 * Removes passed course from the schedule if present and returns true. If it is not present,
	 * returns false.
	 * 
	 * @param courseToAdd the course to be removed.
	 * @return isRemove true if the course is removed, false if course was not present
	 */
	public boolean removeCourseFromSchedule(Course courseToAdd) {
		if (courseToAdd == null) {
			return false;
		}
		
		for (int i = 0; i < schedule.size(); i++) {
			if (courseToAdd.equals(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Resets the schedule by creating a new empty list and resetting the title to the default title
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		
		title = "My Schedule";
	}
	
	/**
	 * Creates a 2D string array where the rows are the courses and the columns are course name, course section, course title, and course
	 * meeting string.
	 * 
	 * @return courseSchedule a 2D array of course information
	 */
	public String[][] getScheduledCourses() {
		String[][] courseSchedule = new String[schedule.size()][COURSE_INFO_INT];
		
		for (int i = 0; i < schedule.size(); i++) {
			String[] currSDA = schedule.get(i).getShortDisplayArray();
			
			courseSchedule[i][0] = currSDA[0];
			courseSchedule[i][1] = currSDA[1];
			courseSchedule[i][2] = currSDA[2];
			courseSchedule[i][3] = currSDA[3];
			courseSchedule[i][4] = currSDA[4];
		}
		
		return courseSchedule;
	}
	
	/**
	 * Sets the schedules title.
	 * 
	 * @param title the string that represents the schedule title.
	 * @throws IllegalArgumentException is title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}
	
	/**
	 * Returns the title of the schedule
	 * 
	 * @return title the String representing this schedule's title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This method is a cumulative sum that returns the total credits in a Schedule.
	 * 
	 * @return total credits in a schedule.
	 */
	public int getScheduleCredits() {
		int creditSum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			Course curr = schedule.get(i);
			
			creditSum = creditSum + curr.getCredits();
		}
		
		return creditSum;
	}
	
	/**
	 * Returns true if the Course can be added to the schedule. False if 
	 * the course is null, already in the schedule, or there is a conflict.
	 * 
	 * @param c course to test eligibility to be added to schedule
	 * @return true if course can be added, false if not
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		
		for (int i = 0; i < schedule.size(); i++) {
			Course curr = schedule.get(i);
			
			if (curr.equals(c)) {
				return false;
			}
			
			try {
				curr.checkConflict(c);
			} catch (ConflictException e) {
				return false;
			}
			
		}
		
		return true;
	}
}
