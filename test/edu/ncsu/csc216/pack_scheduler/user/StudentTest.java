package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Student class
 * @author Luke Early
 */
class StudentTest {
	
	/** Student first name */
	private static final String FIRST_NAME = "First";
	/** Student last name */
	private static final String LAST_NAME = "Last";
	/** Student ID */
	private static final String STUDENT_ID = "flast";
	/** Student email */
	private static final String STUDENT_EMAIL = "flast@ncsu.edu";
	/** Student password */
	private static final String STUDENT_PASSWORD = "Password";
	/** Student's max credits */
	private static final int MAX_CREDITS = 6;
	
	/**
	 * Tests constructing a student object with a value for maxCredits
	 */
	@Test
	public void testStudentWithUniqueCredits() {
		Student s1 = assertDoesNotThrow(
						  () -> new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS),
						  "Should not throw exceptions");
		
		assertAll("Student", 
				() -> assertEquals(FIRST_NAME, s1.getFirstName(), "incorrect first name"), 
				() -> assertEquals(LAST_NAME, s1.getLastName(), "incorrect last name"),
				() -> assertEquals(STUDENT_ID, s1.getId(), "incorrect ID"), 
				() -> assertEquals(STUDENT_EMAIL, s1.getEmail(), "incorrect email"),
				() -> assertEquals(STUDENT_PASSWORD, s1.getPassword(), "incorrect password"),
				() -> assertEquals(MAX_CREDITS, s1.getMaxCredits(), "incorrect max credits"));
	}

	/**
	 * Tests constructing a student object with no value for maxCredits
	 */
	@Test
	public void testStudentWithDefaultCredits() {
		Student s1 = assertDoesNotThrow(
				  () -> new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD),
				  "Should not throw exceptions");

		assertAll("Student", 
				() -> assertEquals(FIRST_NAME, s1.getFirstName(), "incorrect first name"), 
				() -> assertEquals(LAST_NAME, s1.getLastName(), "incorrect last name"),
				() -> assertEquals(STUDENT_ID, s1.getId(), "incorrect ID"), 
				() -> assertEquals(STUDENT_EMAIL, s1.getEmail(), "incorrect email"),
				() -> assertEquals(STUDENT_PASSWORD, s1.getPassword(), "incorrect password"),
				() -> assertEquals(18, s1.getMaxCredits(), "incorrect max credits"));

	}
	
	/**
	 * Test setting first name as null
	 */
	@Test
	public void testSetFirstNameNull() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals("first", s.getFirstName());
	
	}
	
	/**
	 * Tests setting first name empty
	 */
	@Test
	public void testSetFirstNameEmpty() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setFirstName(""));
		assertEquals("Invalid first name", e1.getMessage()); 
		assertEquals("first", s.getFirstName()); 
	
	
	}
	
	/**
	 * Tests setting last name null
	 */
	@Test
	public void testSetLastNameNull() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage()); 
		assertEquals("last", s.getLastName()); 
	}
	
	/**
	 * Test setting last name empty
	 */
	@Test
	public void testSetLastNameEmpty() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setLastName(""));
		assertEquals("Invalid last name", e1.getMessage()); 
		assertEquals("last", s.getLastName()); 
	}
	
	/**
	 * Tests setting email to null
	 */
	@Test
	public void testSetEmailNull() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}
	
	/**
	 * Tests setting email to empty
	 */
	@Test
	public void testSetEmailEmpty() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail(""));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}
	
	/**
	 * Tests setting an email with not '@' char
	 */
	@Test
	public void testSetEmailNoAtChar() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail("emailncsu.edu"));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}
	
	/**
	 * Tests setting an email with no '.' char
	 */
	@Test
	public void testSetEmailNoDotChar() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail("email@ncsuedu"));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}
	
	/**
	 * Tests setting an email with no '@' or '.'
	 */
	@Test
	public void testSetEmailNoDotNoAtChar() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail("emailncsuedu"));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}
	
	/**
	 * Tests setting an email where the last index of the '.' is lower than the index of the '@'
	 */
	@Test
	public void testSetEmailLastDotIndexBad() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setEmail("e.mail@ncsuedu"));
		assertEquals("Invalid email", e1.getMessage()); 
		assertEquals("email@ncsu.edu", s.getEmail()); 
	}

	/**
	 * Tests setting password null
	 */
	@Test
	public void testSetPasswordNull() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage()); 
		assertEquals("hashedpassword", s.getPassword()); 
	}

	/**
	 * Tests setting password empty
	 */
	@Test
	public void testSetPasswordEmpty() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setPassword(""));
		assertEquals("Invalid password", e1.getMessage()); 
		assertEquals("hashedpassword", s.getPassword()); 
	}

	/**
	 * Tests setting credits lower than 3
	 */
	@Test
	public void testSetMaxCreditsTwo() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setMaxCredits(2));
		assertEquals("Invalid max credits", e1.getMessage()); 
		assertEquals(18, s.getMaxCredits()); 
	}
	
	/**
	 * Tests setting credits higher than 18
	 */
	@Test
	public void testSetMaxCreditsNineteen() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
										() -> s.setMaxCredits(19));
		assertEquals("Invalid max credits", e1.getMessage()); 
		assertEquals(18, s.getMaxCredits()); 
	}
	
	/**
	 * Tests hashCode() method
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		
		Student s3 = new Student("Bilbo", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Baggins", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "bbagin", STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "bbagin@shire.edu", STUDENT_PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "GandalfRocks29", MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 4);
		
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		
	}
	
	/**
	 * Tests equals() method
	 */
	@Test
	public void testEqualsObject() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		
		assertEquals(s1, s2);
		assertEquals(s2, s1);
		
		Student s3 = new Student("Bilbo", LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, "Baggins", STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "bbagin", STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, "bbagin@shire.edu", STUDENT_PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, "GandalfRocks29", MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, 4);
		
		assertNotEquals(s1, s3);
		assertNotEquals(s1, s4);
		assertNotEquals(s1, s5);
		assertNotEquals(s1, s6);
		assertNotEquals(s1, s7);
		assertNotEquals(s1, s8);
	}

	/**
	 * Tests toString() method
	 */
	@Test
	public void testToString() {
		// with unique credits
		Student s1 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		assertEquals("First,Last,flast,flast@ncsu.edu,Password,6", s1.toString());
		
		// no credits param
		Student s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD);
		assertEquals("First,Last,flast,flast@ncsu.edu,Password,18", s2.toString());
		
	}
	
	/**
	 * Tests compareTo() method
	 */
	@Test
	public void testCompareTo() {
		// create 2 unique student
		Student s1 = new Student("Luke", "Early", "learly", "l@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, STUDENT_ID, STUDENT_EMAIL, STUDENT_PASSWORD, MAX_CREDITS);
		
		// create 1 student to match
		Student s3 = new Student("Luke", "Early", "learly", "l@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		
		// early before last
		assertEquals(-1, s1.compareTo(s2));
		
		// last after early
		assertEquals(1, s2.compareTo(s1));
		
		assertEquals(0, s3.compareTo(s1));
		assertEquals(0, s1.compareTo(s3));
		
		// Same last name, different first / id
		Student s4 = new Student("Luke", "Early", "learly", "l@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		Student s5 = new Student("Eli", "Early", "eearly", "e@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		
		assertEquals(-1, s5.compareTo(s4));
		assertEquals(1, s4.compareTo(s5));
		
		// Same last name, same first, different id
		Student s6 = new Student("Luke", "Early", "learly", "l@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		Student s7 = new Student("Luke", "Early", "learly2", "l@g.c", STUDENT_PASSWORD, MAX_CREDITS);
		
		// TODO clarify how this works
		assertEquals(1, s7.compareTo(s6));
		assertEquals(-1, s6.compareTo(s7));
		
	}
}
