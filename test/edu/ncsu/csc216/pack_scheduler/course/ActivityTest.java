package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Activity Class, specifically the checkConflict() method.
 * @author Luke Early
 */
class ActivityTest {

	/**
	 * Test method for Activity.checkConflict() where there is not conflict.
	 */
	@Test
	void testCheckConflict() {
		Activity c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 25, "MWF", 1330, 1445);
		Activity c2 = new Course("CSC217", "Software Development Fundamentals lab", "002", 1, "sesmith5", 25, "MWF", 1700, 1800);
		
		assertDoesNotThrow(() -> c1.checkConflict(c2));
		assertDoesNotThrow(() -> c2.checkConflict(c1));
		
	}
	
	/**
	 * Test method for Activity.checkConflict() where there is conflict.
	 */
	@Test
	void testCheckConflictWithConflict() {
		Activity c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 25, "MWF", 1230, 1345);
		Activity c2 = new Course("CSC217", "Software Development Fundamentals lab", "002", 1, "sesmith5", 25, "MWF", 1300, 1400);
		
		
		Exception exception1 = assertThrows(ConflictException.class, () -> c1.checkConflict(c2));
		assertEquals("Schedule conflict.", exception1.getMessage());
		
		Exception exception2 = assertThrows(ConflictException.class, () -> c2.checkConflict(c1));
		assertEquals("Schedule conflict.", exception2.getMessage());
	}
	
	/**
	 * Test method for Activity.checkConflict() where there is not conflict in arranged activities.
	 */
	@Test
	void testCheckConflictWithArranged() {
		Activity c1 = new Course("CSC216", "Software Development Fundamentals", "601", 3, "sesmith5", 25, "A");
		Activity c2 = new Course("CSC116", "Intro to Programming - Java", "601", 3, "jdyoung2", 25, "A");
		
		assertDoesNotThrow(() -> c1.checkConflict(c2));
		assertDoesNotThrow(() -> c2.checkConflict(c1));
	}
	
	/**
	 * Test method for Activity.checkConflict() where there is conflict.
	 */
	@Test
	void testCheckConflictWithStartSameAsEndTime() {
		Activity c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 25, "MWF", 1230, 1345);
		Activity c2 = new Course("CSC116", "Intro to Programming - Java", "601", 3, "jdyoung2", 25, "TWH", 1345, 1500);
		
		Exception exception1 = assertThrows(ConflictException.class, () -> c1.checkConflict(c2));
		assertEquals("Schedule conflict.", exception1.getMessage());
		
		Exception exception2 = assertThrows(ConflictException.class, () -> c2.checkConflict(c1));
		assertEquals("Schedule conflict.", exception2.getMessage());
	}
	
	/**
	 * Test method for Activity.checkConflict() where one activity starts and ends in the time frame of another.
	 */
	@Test
	void testCheckConflictWithTimeInOtherTime() {
		Activity c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 25, "MWF", 1400, 1430);
		Activity c2 = new Course("CSC116", "Intro to Programming - Java", "601", 3, "jdyoung2", 25, "TWH", 1345, 1500);
		
		Exception exception1 = assertThrows(ConflictException.class, () -> c1.checkConflict(c2));
		assertEquals("Schedule conflict.", exception1.getMessage());
		
		Exception exception2 = assertThrows(ConflictException.class, () -> c2.checkConflict(c1));
		assertEquals("Schedule conflict.", exception2.getMessage());
	}

}
