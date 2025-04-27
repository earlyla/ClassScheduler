package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the Student object.
 * @author SarahHeckman
 */
public class StudentTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * Tests canAdd() method
	 */
	@Test
	void testCanAdd() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 9);
		Course testCourse1 = new Course("CSC216", "Intro to Software Dev", "001", 3, "sheckman", 25, "MW", 1000, 1130);
		Course testCourse2 = new Course("CSC217", "Lab", "003", 3, "mzahn", 25, "TH", 1000, 1130);
		// conflict course
		Course testCourse3 = new Course("CSC226", "Discrete", "002", 3, "awatkins", 25, "TH", 1000, 1130);
		// good course
		Course testCourse4 = new Course("CSC230", "class", "002", 3, "teacher", 25, "F", 1000, 1130);
		// too many credits
		Course testCourse5 = new Course("CSC246", "class", "004", 3, "teacher", 25, "F", 1300, 1400);
		
		// adding a null course
		assertFalse(s1.canAdd(null));
		
		// adding real courses
		assertTrue(s1.canAdd(testCourse1));
		s1.getSchedule().addCourseToSchedule(testCourse1);
		
		assertTrue(s1.canAdd(testCourse2));
		s1.getSchedule().addCourseToSchedule(testCourse2);
		
		// adding course already on schedule
		assertFalse(s1.canAdd(testCourse2));
		
		// adding a conflict course
		assertFalse(s1.canAdd(testCourse3));
		
		// adding a 3rd and final
		assertTrue(s1.canAdd(testCourse4));
		s1.getSchedule().addCourseToSchedule(testCourse4);
		
		// adding a course after max credits
		assertFalse(s1.canAdd(testCourse5));
		
	}

}
