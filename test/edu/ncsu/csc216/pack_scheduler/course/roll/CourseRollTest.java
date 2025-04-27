package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the CourseRoll class.
 */
class CourseRollTest {
	/** valid sutdent dir */
	private static final String VALID_STUDENT_DIRECTORY = "test-files/student_records_long.txt";
	/** course to be used for testing*/
	private Course c1 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sheckman", 22, "MW", 1330, 1445);
	/** extra student 1 */
	private Student s1 = new Student("s1first", "s1last", "s1id", "s1@e.c", "s1pw");
	/** extra student 2 */
	private Student s2 = new Student("s2first", "s2last", "s2id", "s2@e.c", "s2pw");
	/** student directory */
	private StudentDirectory sd1;
	
	/** 
	 * sets up a course with a course roll of 12 students 
	 */	
	@BeforeEach
	void setUp() {
		sd1 = new StudentDirectory();
		sd1.loadStudentsFromFile(VALID_STUDENT_DIRECTORY);
		
		String[][] studentDir = sd1.getStudentDirectory();
		
		for (int i = 0; i < studentDir.length; i++) {
			String currStudentId = studentDir[i][2];
			Student curr = sd1.getStudentById(currStudentId);
			
			c1.getCourseRoll().enroll(curr);
		}
	}
	
	/**
	 * Tests the CourseRoll constructor
	 */
	@Test
	void testCourseRoll() {
		CourseRoll courseRoll = new CourseRoll(c1, 30);
		
		assertEquals(30, courseRoll.getEnrollmentCap());
		
		assertThrows(IllegalArgumentException.class,
				() -> new CourseRoll(null, 30));
		
	}

	/**
	 * tests getEnrollmentCap method
	 */
	@Test
	void testGetEnrollmentCap() {
		assertEquals(22, c1.getCourseRoll().getEnrollmentCap());
	}

	/**
	 * tests setEnrollmentCap method
	 */
	@Test
	void testSetEnrollmentCap() {
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().setEnrollmentCap(12));
		
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().setEnrollmentCap(8));
		
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().setEnrollmentCap(300));
		
	}

	/**
	 * tests enroll method
	 */
	@Test
	void testEnroll() {
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().enroll(null));
		
		assertEquals(22, c1.getCourseRoll().getEnrollmentCap() - c1.getCourseRoll().getOpenSeats());
		
		assertEquals(9, c1.getCourseRoll().getNumberOnWaitlist());
		
		c1.getCourseRoll().enroll(s1);
		
		assertEquals(10, c1.getCourseRoll().getNumberOnWaitlist());
		
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().enroll(s2));
	}

	/**
	 * Tests drop method
	 */
	@Test
	void testDrop() {
		c1.getCourseRoll().drop(sd1.getStudentById("dajjtin"));
		c1.getCourseRoll().drop(sd1.getStudentById("daustasdfin"));
		c1.getCourseRoll().drop(sd1.getStudentById("lbeasdfarg"));
		c1.getCourseRoll().drop(sd1.getStudentById("daustin"));
		assertEquals(5, c1.getCourseRoll().getNumberOnWaitlist());
		assertEquals(0, c1.getCourseRoll().getOpenSeats());
		
		c1.getCourseRoll().drop(sd1.getStudentById("rbrennan"));
		c1.getCourseRoll().drop(sd1.getStudentById("efrost"));
		c1.getCourseRoll().drop(sd1.getStudentById("lberg"));
		c1.getCourseRoll().drop(sd1.getStudentById("lbjg"));
		c1.getCourseRoll().drop(sd1.getStudentById("rjennan"));
		c1.getCourseRoll().drop(sd1.getStudentById("dnolan"));
		
		assertEquals(0, c1.getCourseRoll().getNumberOnWaitlist());
		assertEquals(1, c1.getCourseRoll().getOpenSeats());
		
		c1.getCourseRoll().drop(sd1.getStudentById("jking"));
		c1.getCourseRoll().drop(sd1.getStudentById("chwartz"));
		
		assertEquals(0, c1.getCourseRoll().getNumberOnWaitlist());
		assertEquals(3, c1.getCourseRoll().getOpenSeats());
		
	}

}
