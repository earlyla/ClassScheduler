package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class ScheduleTest {

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
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	@Test
	void testSchedule() {
		Schedule testSchedule = new Schedule();
		
		assertEquals("My Schedule", testSchedule.getTitle());
		assertEquals(0, testSchedule.getScheduledCourses().length);
	}

	@Test
	void testAddCourseToSchedule() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		
		String[][] schedule = testSchedule.getScheduledCourses();
		
		assertEquals(schedule[0][0], NAME);
		
	}

	@Test
	void testRemoveCourseFromSchedule() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course testCourse2 = null;
		Course testCourse3 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		
		// add to schedule
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		String[][] schedule = testSchedule.getScheduledCourses();
		assertEquals(schedule[0][0], NAME);
		
		
		// remove course that is null
		assertFalse(testSchedule.removeCourseFromSchedule(testCourse2));
		
		// remove course that is not present
		assertFalse(testSchedule.removeCourseFromSchedule(testCourse3));
		
		// remove from schedule
		assertTrue(testSchedule.removeCourseFromSchedule(testCourse1));
		assertEquals(testSchedule.getScheduledCourses().length, 0);
		
	}

	@Test
	void testResetSchedule() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		// add to schedule
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		String[][] schedule = testSchedule.getScheduledCourses();
		assertEquals(schedule[0][0], NAME);
		
		testSchedule.setTitle("Luke's Schedule");
		assertEquals("Luke's Schedule", testSchedule.getTitle());
		
		// reset schedule
		testSchedule.resetSchedule();
		assertEquals("My Schedule", testSchedule.getTitle());
		assertEquals(0, testSchedule.getScheduledCourses().length);
		
		
	}

	@Test
	void testGetScheduledCourses() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		// add to schedule
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		
		String[][] schedule = testSchedule.getScheduledCourses();
		assertEquals(schedule[0][0], NAME);
		assertEquals(schedule[0][1], SECTION);
		assertEquals(schedule[0][2], TITLE);
	}

	@Test
	void testSetTitle() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		// adds a course to an unnamed schedule
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		
		testSchedule.setTitle("Luke's Schedule");
		assertEquals("Luke's Schedule", testSchedule.getTitle());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
								() -> testSchedule.setTitle(null));
		
		assertEquals(e1.getMessage(), "Title cannot be null.");
	}

	@Test
	void testGetTitle() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		testSchedule.setTitle("Luke's Schedule");
		
		// adds a course to a named schedule
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		
		// gets title from a schedule with a course
		assertEquals("Luke's Schedule", testSchedule.getTitle());
	}
	
	@Test
	void testGetScheduleCredit() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course testCourse2 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Course testCourse3 = new Course("CSC226", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "F", START_TIME, END_TIME);
		
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		assertTrue(testSchedule.addCourseToSchedule(testCourse2));
		assertTrue(testSchedule.addCourseToSchedule(testCourse3));
		
		assertEquals(9, testSchedule.getScheduleCredits());
	}
	
	@Test
	void testCanAdd() {
		Schedule testSchedule = new Schedule();
		Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course testCourse2 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Course testCourse3 = new Course("CSC226", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "F", START_TIME, END_TIME);
		
		assertTrue(testSchedule.addCourseToSchedule(testCourse1));
		assertTrue(testSchedule.addCourseToSchedule(testCourse2));
		
		// Can add - true
		assertTrue(testSchedule.canAdd(testCourse3));
		assertTrue(testSchedule.addCourseToSchedule(testCourse3));
		
		// Can't add, duplicate
		assertFalse(testSchedule.canAdd(testCourse3));
		
		// Can't add, null	
		assertFalse(testSchedule.canAdd(null));
		
	}

}
