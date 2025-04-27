package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests for CourseCatalog class
 * 
 * @author Luke Early
 */
class CourseCatalogTest {

	/** 10 valid course records */
	private final String validTestFileLong = "test-files/course_records_10.txt";
	/** 6 valid course records */
	private final String validTestFileShort = "test-files/course_records_6.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 25;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	@Test
	void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests newCourseCatalog.
	 */
	@Test
	void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileLong);
		assertEquals(10, cc.getCourseCatalog().length);
		
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
		
	}

	/**
	 * Tests loadCourseFromFile()
	 */
	@Test
	void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileLong);
		assertEquals(10, cc.getCourseCatalog().length);
	}

	/**
	 * Tests adding a new course to course catalog
	 */
	@Test
	void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileLong);
		assertEquals(10, cc.getCourseCatalog().length);
		
		// adds a course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(11, cc.getCourseCatalog().length);
		
	}

	/**
	 * Tests removeCourseFromCatalog
	 */
	@Test
	void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileLong);
		assertEquals(10, cc.getCourseCatalog().length);
		
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(11, cc.getCourseCatalog().length);
		
		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(10, cc.getCourseCatalog().length);
		
		assertFalse(cc.removeCourseFromCatalog("CSC 123", SECTION));
		assertEquals(10, cc.getCourseCatalog().length);
		
	}

	/**
	 * Tests getCourseFromCatalog
	 */
	@Test
	void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileLong);
		assertEquals(10, cc.getCourseCatalog().length);
		
		// adds course we will look for
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(11, cc.getCourseCatalog().length);
		
		// ensures course that is added is the same course we added
		Course courseToCompare = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(courseToCompare, cc.getCourseFromCatalog(NAME, SECTION));
		
		// ensures null is returned on a non-existant course
		assertEquals(null, cc.getCourseFromCatalog("abc 123", "003"));
				
	}

	@Test
	void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFileShort);
		assertEquals(6, cc.getCourseCatalog().length);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("003", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC216", catalog[1][0]);
		assertEquals("601", catalog[1][1]);
		assertEquals("Software Development Fundamentals", catalog[1][2]);
		//Row 2
		assertEquals("CSC217", catalog[2][0]);
		assertEquals("202", catalog[2][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[2][2]);
		//Row 3
		assertEquals("CSC226", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[3][2]);
		//Row 11
		assertEquals("CSC230", catalog[4][0]);
		assertEquals("001", catalog[4][1]);
		assertEquals("C and Software Tools", catalog[4][2]);
		//Row 12
		assertEquals("CSC316", catalog[5][0]);
		assertEquals("001", catalog[5][1]);
		assertEquals("Data Structures and Algorithms", catalog[5][2]);
		
	}

	@Test
	void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 25, "MW", 1250, 1440);
		assertEquals(1, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_course_record_1.txt");
		checkFiles("test-files/expected_course_record_1.txt", "test-files/actual_course_record_1.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
