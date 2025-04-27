package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the Course class.
 * 
 * Note that test methods for all getters have been omitted. They will be tested
 * as we test other methods.
 * 
 * @author Sarah Heckman
 */
public class CourseTest {

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
	/** student directory */
	private static final String VALID_STUDENT_DIRECTORY = "test-files/student_records.txt";

	/**
	 * Tests constructing a Course with meeting days and times.
	 */
	@Test
	public void testCourseWithTimes() {
		// Test a valid construction
		Course c = assertDoesNotThrow(
				() -> new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME),
				"Should not throw exception");

		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));
	}

	/**
	 * Tests constructing an arranged course.
	 */
	@Test
	public void testCourseArranged() {
		// Test a valid construction and make sure values are correct
		Course c = assertDoesNotThrow(() -> new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A"),
				"Should not throw exception");

		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals("A", c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(0, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(0, c.getEndTime(), "incorrect end time"));
	}

	/**
	 * Tests setName(). This can ONLY be done through the Course constructor.
	 * The test only considers valid values.
	 * @param courseName valid course name to test
	 */
	@ParameterizedTest
	@ValueSource(strings = {"CSC216", "E115", "MA141", "HESF101", "CSC116"})
	public void testSetNameValid(String courseName) {

		// Testing valid names
		Course course = assertDoesNotThrow(
				() -> new Course(courseName, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME),
				"Should not throw exception");
		assertEquals(courseName, course.getName(), "Failed test with valid course name - " + courseName);
	}
	
	/**
	 * Tests setName(). This can ONLY be done through the Course constructor.
	 * The test only considers invalid values, which should throw IllegalArgumentExceptions.
	 * @param invalidCourseName invalid course name to test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"E 11", "HESFQ 101", "101", "CSC 216", "101ext", "HESFQ101", "HSEF01", "CSC 2167", " CSC 216", "CSC\t216", "C!C 216", "CSC 21!"})
	public void testSetNameInvalid(String invalidCourseName) {
		// Testing for null name - IAE should be thrown
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Course(invalidCourseName, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid course name.", e1.getMessage(), "Incorrect exception thrown with invalid course name - " + invalidCourseName);
	}

	/**
	 * Tests setTitle().
	 */
	@Test
	public void testSetTitleValid() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));


		// Valid set
		c.setTitle("A new title");
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals("A new title", c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));
	}
	
	/** 
	 * Tests setTitle with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	public void testSetTitleInvalid(String invalid) {

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Course(NAME, invalid, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid title.", exception.getMessage(), "Incorrect exception thrown with invalid input - " + invalid);
	}

	/**
	 * Tests setSection().
	 */
	@Test
	public void testSetSectionValid() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));

		// Test valid section
		c.setSection("002");
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals("002", c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));
	}
	
	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"00", "0012", "abc"})
	public void testSetSectionInvalid(String invalid) {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> c.setSection(invalid));
		assertEquals("Invalid section.", exception.getMessage(), "Incorrect exception thrown with invalid input - " + invalid);
	}


	/**
	 * Tests setCredits().
	 */
	@Test
	public void testSetCreditsValid() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));

		// Test valid credits
		c.setCredits(4);
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(4, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));
	}
	
	/**
	 * Tests setCredits with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@ValueSource(ints = {0, 6})
	public void testSetCreditsInvalid(int invalid) {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> c.setCredits(invalid));
		assertEquals("Invalid credits.", exception.getMessage(), "Incorrect exception thrown with invalid input - " + invalid);
	}

	/**
	 * Tests setInstructorId().
	 */
	@Test
	public void testSetInstructorIdValid() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));

		// Test valid instructor id
		c.setInstructorId("jctetter");
		assertAll("Course", 
				() -> assertEquals(NAME, c.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c.getCredits(), "incorrect credits"),
				() -> assertEquals("jctetter", c.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c.getEndTime(), "incorrect end time"));
	}
	
	/**
	 * Tests setInstructorId with invalid input.
	 */
	@Test
	public void testSetInstructorIdInvalid() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		assertThrows(IllegalArgumentException.class,
				() -> c.setInstructorId(""));
	}


	/**
	 * Tests setMeetingDaysAndTime().
	 * @param meetingString valid meeting string
	 * @param startTime valid start time
	 * @param endTime valid end time
	 * @param expectedStartTime expected start time from the first three arguments
	 * @param expectedEndTime expected end time from the first three arguments
	 */
	@ParameterizedTest(name = "{index} => meetingString={0}, startTime={1}, endTime={2}, expectedStartTime={3}, expectedEndTime={4}")
	@CsvSource({
		"TH,1300,1445,1300,1445",
		"MF,1300,1445,1300,1445",
		"MF,1015,1445,1015,1445",
		"MF,1015,1130,1015,1130",
		"W,830,945,830,945",
		"H,1130,1245,1130,1245",
		"A,0,0,0,0"})
	public void testSetMeetingDaysAndTimesValid(String meetingString, int startTime, int endTime, int expectedStartTime, int expectedEndTime) {

		// Test valid course with meeting times (not arranged)
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c1.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c1.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c1.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c1.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c1.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c1.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c1.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c1.getEndTime(), "incorrect end time"));

		// Test valid course with arranged
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		assertAll("Course", 
				() -> assertEquals(NAME, c2.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c2.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c2.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c2.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c2.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals("A", c2.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(0, c2.getStartTime(), "incorrect start time"),
				() -> assertEquals(0, c2.getEndTime(), "incorrect end time"));

		c1.setMeetingDaysAndTime(meetingString, startTime, endTime);
		assertEquals(meetingString, c1.getMeetingDays());
		assertEquals(expectedStartTime, c1.getStartTime());
		assertEquals(expectedEndTime, c1.getEndTime());

		c2.setMeetingDaysAndTime(meetingString, startTime, endTime);
		assertEquals(meetingString, c2.getMeetingDays());
		assertEquals(expectedStartTime, c2.getStartTime());
		assertEquals(expectedEndTime, c2.getEndTime());
	}	
	
	/**
	 * Tests invalid meeting days and times
	 * @param meetingString valid meeting string
	 * @param startTime valid start time
	 * @param endTime valid end time
	 */
	@ParameterizedTest(name = "{index} => meetingString={0}, startTime={1}, endTime={2}")
	@CsvSource({
		"AM,1330,1445",
		"XYZ,1330,1445",
		"m,1330,1445",
		"MTWS,1330,1445",
		"MWM,1330,1445",
		"TMT,1330,1445",
		"WFW,1330,1445",
		"MHH,1330,1445",
		"MWFTF,1330,1445",
		"MW,-1,1445",
		"MW,1330,-1",
		"MW,2400,1445",
		"MW,1330,2400",
		"MW,1360,1445",
		"MW,1330,1360",
		"MW,2300,1445",
		"MW,1330,1200",
		"A,1300,1445",
		"A,300,0",
		"A,0,1300"
	})
	public void testSetMeetingDaysAndTimesInvalid(String meetingString, int startTime, int endTime) {
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertAll("Course", 
				() -> assertEquals(NAME, c1.getName(), "incorrect name"), 
				() -> assertEquals(TITLE, c1.getTitle(), "incorrect title"),
				() -> assertEquals(SECTION, c1.getSection(), "incorrect section"), 
				() -> assertEquals(CREDITS, c1.getCredits(), "incorrect credits"),
				() -> assertEquals(INSTRUCTOR_ID, c1.getInstructorId(), "incorrect instructor id"),
				() -> assertEquals(MEETING_DAYS, c1.getMeetingDays(), "incorrect meeting days"), 
				() -> assertEquals(START_TIME, c1.getStartTime(), "incorrect start time"),
				() -> assertEquals(END_TIME, c1.getEndTime(), "incorrect end time"));
		
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> c1.setMeetingDaysAndTime(meetingString, startTime, endTime));
		assertEquals("Invalid meeting days and times.", exception.getMessage(), "Incorrect exception thrown with invalid input.");
		assertEquals(MEETING_DAYS, c1.getMeetingDays(), "incorrect meeting days");
		assertEquals(START_TIME, c1.getStartTime(), "incorrect start time");
		assertEquals(END_TIME, c1.getEndTime(), "incorrect end time");
		
	}

	/**
	 * Tests getMeetingString().
	 */
	@Test
	public void testGetMeetingString() {

		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 900, 1035);
		assertEquals("MW 9:00AM-10:35AM", c2.getMeetingString());
		Course c3 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		assertEquals("Arranged", c3.getMeetingString());
		Course c4 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", 1145, 1425);
		assertEquals("TH 11:45AM-2:25PM", c4.getMeetingString());
		Course c5 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", 1200, 1300);
        assertEquals("TH 12:00PM-1:00PM", c5.getMeetingString());
	}

	/**
	 * Tests that the equals method works for all Course fields.
	 */
	@Test
	public void testEqualsObject() {
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Course c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
		Course c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);
		Course c10 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		// Test for equality in both directions
		assertEquals(c1, c2);
		assertEquals(c2, c1);

		// Test for each of the fields
		assertNotEquals(c1, c3);
		assertNotEquals(c1, c4);
		assertNotEquals(c1, c5);
		assertNotEquals(c1, c6);
		assertNotEquals(c1, c7);
		assertNotEquals(c1, c8);
		assertNotEquals(c1, c9);
		assertNotEquals(c1, c10);
	}

	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
		Course c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
		Course c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);
		Course c10 = new Course("CSC217", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);


		// Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());

		// Test for each of the fields
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
		assertNotEquals(c1.hashCode(), c10.hashCode());
	}

	/**
	 * Tests that toString returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		String s1 = "CSC216,Software Development Fundamentals,001,3,sesmith5,25,MW,1330,1445";
		assertEquals(s1, c1.toString());

		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		String s2 = "CSC216,Software Development Fundamentals,001,3,sesmith5,25,A";
		assertEquals(s2, c2.toString());
	}
	
	/**
	 * Tests that the compareTo method returns -1 if compared course should be before, 0 if they are the same, and 1 if it should
	 * come after based on course name and then section.
	 */
	@Test
	public void testCompareTo() {
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math for CS", "001", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MWF", 935, 1025);
		Course c3 = new Course("CSC226", "Discrete Math for CS", "001", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MWF", 935, 1025);
		
		// c1 should be less than c2, return -1
		assertEquals(-1, c1.compareTo(c2));
		
		// c2 should be greater than c2, return 1
		assertEquals(1, c2.compareTo(c1));
		
		// c2 and c3 should be equal, return 0
		assertEquals(0, c2.compareTo(c3));
		
	}
	
	/**
	 * Tests that the compareTo method returns -1 if compared course should be before, 0 if they are the same, and 1 if it should
	 * come after based on course name and then section.
	 */
	@Test
	public void testGetShortDisplay() {
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW", 1330, 1445);		
		
		assertEquals(c1.getShortDisplayArray()[0], "CSC216");
		assertEquals(c1.getShortDisplayArray()[1], "002");
		assertEquals(c1.getShortDisplayArray()[2], "Software Development Fundamentals");
		assertEquals(c1.getShortDisplayArray()[3], "MW 1:30PM-2:45PM");
		assertEquals(c1.getShortDisplayArray()[4], "25");
		
	}
	
	/**
	 * Tests that the compareTo method returns -1 if compared course should be before, 0 if they are the same, and 1 if it should
	 * come after based on course name and then section.
	 */
	@Test
	public void testGetLongDisplay() {
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW", 1330, 1445);		
		
		assertEquals(c1.getLongDisplayArray()[0], "CSC216");
		assertEquals(c1.getLongDisplayArray()[1], "002");
		assertEquals(c1.getLongDisplayArray()[2], "Software Development Fundamentals");
		assertEquals(c1.getLongDisplayArray()[3], "3");
		assertEquals(c1.getLongDisplayArray()[4], "sesmith5");
		assertEquals(c1.getLongDisplayArray()[5], "MW 1:30PM-2:45PM");
		assertEquals(c1.getLongDisplayArray()[6], "");
		
	}
	
	/**
	 * Tests enrollmentCap and CourseRoll
	 */
	@Test
	public void testEnrollmentCap() {
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "MW", 1330, 1445);		
		assertEquals(25, c1.getCourseRoll().getOpenSeats());
		
		// Exception handling
		assertThrows(IllegalArgumentException.class,
						() -> new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, 9, "MW", 1330, 1445));		
			
		assertThrows(IllegalArgumentException.class,
				() -> new Course("CSC216", "Software Development Fundamentals", "002", CREDITS, INSTRUCTOR_ID, 300, "MW", 1330, 1445));
		
		// add students, attempt to change maxCap to less than size() of courseRoll
		StudentDirectory sd1 = new StudentDirectory();
		sd1.loadStudentsFromFile(VALID_STUDENT_DIRECTORY);
		Student s1 = new Student("s1first", "s1last", "s1id", "s1@e.c", "s1pw");
		Student s2 = new Student("s2first", "s2last", "s2id", "s2@e.c", "s2pw");
		
		String[][] studentDir = sd1.getStudentDirectory();
		
		for (int i = 0; i < studentDir.length; i++) {
			String currStudentId = studentDir[i][2];
			Student curr = sd1.getStudentById(currStudentId);
			
			c1.getCourseRoll().enroll(curr);
		}
		
		c1.getCourseRoll().enroll(s1);
		c1.getCourseRoll().enroll(s2);
		
		int numStudents = c1.getCourseRoll().getEnrollmentCap() - c1.getCourseRoll().getOpenSeats();
		assertEquals(12, numStudents);
		
		assertThrows(IllegalArgumentException.class,
				() -> c1.getCourseRoll().setEnrollmentCap(10));
				
	}
}