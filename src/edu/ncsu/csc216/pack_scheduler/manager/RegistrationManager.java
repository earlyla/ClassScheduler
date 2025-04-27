package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * Manages registration, provides a single instance for the GUI to interact with.
 * 
 * @author Luke Early
 * @author Unkown Colleague
 * 
 */
public class RegistrationManager {
	/** instance of RegistrationManager */
	private static RegistrationManager instance;
	/** instance of CourseCatalog */
	private CourseCatalog courseCatalog;
	/** instance of StudentDirectory */
	private StudentDirectory studentDirectory;
	/** User type registrar*/
	private User registrar;
	/** User type for whoever the current user is */
	private User currentUser;
	/** instance of Faculty directory */
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Class constant for the properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructor for the RegistrationManager
	 */
	private RegistrationManager() {
		createRegistrar();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		courseCatalog = new CourseCatalog();
	}
	
	/**
	 * Creates a Registrar object, a type of User.
	 * 
	 * @throws IllegalArgumentException "Cannot create registrar." if:
	 * 									- input throws an IOException due to an issue with the properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			String hashPW = hashPW(prop.getProperty("pw"));
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hashes a String password using the SHA-256 algorithm.
	 * 
	 * @param pw String to be hashed
	 * @return String hashed password
	 * @throws IllegalArgumentException "Cannot hash password." if:
	 * 									- if digest1 throws an NSAException due to an issue with the hash algo.
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
			
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		
		}
	}
	
	/**
	 * Gets the current instance of the RegistrationManager.
	 * 
	 * @return instance the instantiated RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Get the course catalog
	 * 
	 * @return courseCatalog the CourseCatalog object
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Gets the student directory
	 * 
	 * @return studentDirectory the StudentDirectory object
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Gets the faculty directory
	 * 
	 * @return facultyDirectory the StudentDirectory object
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Allows users to login to utilize the Scheduler.
	 * 
	 * Determines type of User, if Registrar returns true.
	 * 
	 * @param id user's ID
	 * @param password user's password
	 * @return true if user is a registrar, false if a student
	 * @throws IllegalArgumentException "User doesn't exist." if:
	 * 									- id is not found in current studentDirectory
	 */
	public boolean login(String id, String password) {
		String localHashPW = hashPW(password);
		
		if (getCurrentUser() != null) {
			return false;
		}
		
		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
			return false;
		}
		
		try {
			Student s = studentDirectory.getStudentById(id);
			Faculty f = facultyDirectory.getFacultyById(id);
			if (s != null && s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			} else if (f != null && f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("User doesn't exist.", e);
		}
		
		return false;
	}
	
	/**
	 * Logs user out of the scheduler by setting currentUser to null
	 */
	public void logout() {
		currentUser = null;
	}
	
	/**
	 * Returns the current user.
	 * 
	 * @return currentUser the current user of the scheduler
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears the courseCatalog and studentDirectory completely by creating new instances of each
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Inner class representing a Registrar object.
	 * 
	 * Registrar is a type of User with different behavior than a User of type Student
	 * 
	 * @author Unkown Colleague
	 */
	private static class Registrar extends User {
		/**
		 * Constructs a Registrar object
		 * 
		 * @param firstName registrar's first name
		 * @param lastName registrar's last name
		 * @param id registrar's id
		 * @param email registrar's email
		 * @param hashPW registrar's hashedPW
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}
	
	/**
	 * adds specified course to the faculty memeber's schedule, as long as the registrar is currently logged in.
	 * 
	 * @param course to add to faculty schedule
	 * @param faculty member whose schedule will gain course
	 * @return true if able to add course, false otherwise
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		} else {
			return faculty.getSchedule().addCourseToSchedule(course);
		}
	}
	
	/**
	 * removes specified course from the faculty member's schedule, as long as the registrar is currently logged in.
	 * 
	 * @param course to remove from faculty schedule
	 * @param faculty member whose schedule will lose course
	 * @return true if able to remove course, false otherwise
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		} else {
			return faculty.getSchedule().removeCourseFromSchedule(course);
		}
	}
	
	/**
	 * resets faculty member's schedule, as long as the registrar is currently logged in.
	 * 
	 * @param faculty member whose schedule will reset
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser == null || currentUser != registrar) {
			throw new IllegalArgumentException();
		} else {
			faculty.getSchedule().resetSchedule();
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
}