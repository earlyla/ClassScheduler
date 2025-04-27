package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class handles creating a catalog consisting off all the courses available to students
 * 
 * @author Luke Early
 */
public class CourseCatalog {
	/** List of courses in the directory */
	private SortedList<Course> catalog;
	
	/**
	 * Creates an empty course catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Creates a new empty course catalog. All previously added courses are lost unless saved.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads courses from a file and adds them to the catalog
	 * 
	 * @param fileName name of file to load courses from.
	 * @throws IllegalArgumentException if filename is invalid
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a course to the catalog. Returns true if the course is added and false if the course is not added. 
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
	 * @return true if the course is added, false if the course is already in the catalog
	 * @throws IllegalArgumentException if any propagate from the Course constructor
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		
		// constructs the course to add to the catalog
		Course courseToAdd = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		
		// checks if course is already in the catalog, if not adds course and returns true
		if (getCourseFromCatalog(name, section) != null) {
			return false;
		} else {
			catalog.add(courseToAdd);
			return true;
		}
		
	}
	
	/**
	 * Removes the course with the specified name from the catalog.
	 * 
	 * If the course is not present, returns false.
	 * 
	 * @param name course name
	 * @param section course section
	 * @return true if removed, false if not present (therefore nothing removed)
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If present, returns course in catalog with matching name and section.
	 * 
	 * If not present, returns null.
	 * 
	 * @param name name of a course
	 * @param section course's section
	 * @return courseToFind the course object which matches the name and section parameters passed by the 
	 * 			   method, null if it's not present
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course courseToFind = catalog.get(i);
			if (courseToFind.getName().equals(name) && courseToFind.getSection().equals(section)) {
				return courseToFind;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns a 2D array containing the name, section, and title, and meetingString
	 * of each course in catalog. 
	 * 
	 * Returns null 2D array if catalog is empty.
	 * 
	 * @return catalog2D a 2D array which contains each course in a row and the name, section, 
	 * 					 title and meeting String of said course in each column
	 */
	public String[][] getCourseCatalog() {
		String[][] catalog2D = new String[catalog.size()][5];
		
		if (catalog == null || catalog.size() == 0) {
			return catalog2D;
		}
		
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			
			catalog2D[i][0] = c.getName();
			catalog2D[i][1] = c.getSection();
			catalog2D[i][2] = c.getTitle();
			catalog2D[i][3] = c.getMeetingString();
			catalog2D[i][4] = c.getCourseRoll().getOpenSeats() + "";
			
		}
		
		return catalog2D;
	}
	
	/**
	 * Saves all course in the catalog to a file.
	 * 
	 * @param fileName name of file to save courses to.
	 * @throws IllegalArgumentException if unable to write to fileName
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
