package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * tests the faculty object
 */
class FacultyTest {

	/**
	 * tests constructor, toString
	 */
	@Test
	void testFacultyConstructor() {
		Faculty teach1 = new Faculty("fn", "ln", "facId", "f@n.e", "pw", 2);
		
		assertEquals("fn,ln,facId,f@n.e,pw,2", teach1.toString());
	}
	
	/**
	 * tests equals
	 */
	@Test
	void testEquals() {
		Faculty teach1 = new Faculty("fn", "ln", "facId", "f@n.e", "pw", 2);
		Faculty teach2 = new Faculty("fn", "ln", "facId", "f@n.e", "pw", 2);
		Faculty teach3 = new Faculty("name", "ln", "facId", "f@n.e", "pw", 2);
		Faculty teach4 = new Faculty("name", "ln", "facId", "f@n.e", "pw", 3);
		
		assertTrue(teach1.equals(teach2));
		assertTrue(teach2.equals(teach1));
		
		assertFalse(teach1.equals(teach3));
		assertFalse(teach3.equals(teach1));
		
		assertFalse(teach3.equals(teach4));
		assertFalse(teach4.equals(teach3));
		
	}
	
	/**
	 * tests hashCode
	 */
	@Test
	void testHashCode() {
		Faculty teach1 = new Faculty("fn", "ln", "facId", "f@n.e", "pw", 2);
		Faculty teach2 = new Faculty("fn", "ln", "facId", "f@n.e", "pw", 2);
		Faculty teach3 = new Faculty("name", "ln", "facId", "f@n.e", "pw", 2);
		
		int t1Hc = teach1.hashCode();
		int t2Hc = teach2.hashCode();
		int t3Hc = teach3.hashCode();
		
		assertEquals(t1Hc, t2Hc);
		
		assertNotEquals(t2Hc, t3Hc);
	}
	

}
